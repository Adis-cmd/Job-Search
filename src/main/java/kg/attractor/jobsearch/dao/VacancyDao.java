package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.exception.NumberFormatException.CategoryServiceException;
import kg.attractor.jobsearch.exception.NumberFormatException.UserServiceException;
import kg.attractor.jobsearch.exception.NumberFormatException.VacancyServiceException;
import kg.attractor.jobsearch.model.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VacancyDao {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Optional<Vacancy> getVacancyById(Long vacancyId) {
        String sql = "select * from vacancy where id = ?";
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), vacancyId)
                )
        );
    }

    public List<Vacancy> getAllVacancies() {
        String sql = "select * from vacancy";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
    }

    public List<Vacancy> getVacanciesByCategory(Long categoryId) {
        String sqlCheck = "SELECT COUNT(*) FROM category WHERE id = ?";
        Integer userCount = jdbcTemplate.queryForObject(sqlCheck, Integer.class, categoryId);

        if (userCount == 0 || userCount == null) {
            throw new CategoryServiceException("Категория с таким Id не найден");
        }

        String sql = "select * from vacancy where categoryId = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), categoryId);
    }

    public List<Vacancy> getVacancyByApplicant() {
        String sql = "select * from vacancy " +
                "Left join responded_applicant a on vacancy.id = a.vacancyId" +
                " where vacancy.id = a.vacancyId";
        return  jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
    }


    public void createVacancy(Vacancy vacancy, Long authorId) {
        String sqlCheck = "SELECT COUNT(*) FROM users WHERE id = ?";

        Integer userCount = jdbcTemplate.queryForObject(sqlCheck, Integer.class, authorId);

        if (userCount == 0 || userCount == null) {
            throw new UserServiceException("Пользователь с таким Id не найден");
        }

        String sqlCheckCategory = "SELECT COUNT(*) FROM category WHERE id = ?";
        Integer categoryCount = jdbcTemplate.queryForObject(sqlCheckCategory, Integer.class, vacancy.getCategoryId());

        if (categoryCount == null || categoryCount == 0) {
            throw new VacancyServiceException("Категория с таким ID не существует.");
        }


        String sql = "insert into vacancy " +
                "(name, description, categoryId, salary, expFrom, expTo, isActive, authorId, createdDate, updatedTime)" +
                " values (:name, :description, :categoryId, :salary, :expFrom, :expTo, :isActive, :authorId, :createdDate, :updatedTime)";

        namedParameterJdbcTemplate.update(
                sql,
                new MapSqlParameterSource()
                        .addValue("name", vacancy.getName())
                        .addValue("description", vacancy.getDescription())
                        .addValue("categoryId", vacancy.getCategoryId())
                        .addValue("salary", vacancy.getSalary())
                        .addValue("expFrom",vacancy.getExpFrom())
                        .addValue("expTo", vacancy.getExpTo())
                        .addValue("isActive", vacancy.getIsActive())
                        .addValue("authorId", authorId)
                        .addValue("createdDate", LocalDateTime.now())
                        .addValue("updatedTime",  LocalDateTime.now())
        );
    }
    public Boolean isVacancyOwnedByUser(Long vacancyId, Long userId) {
        String sql = "SELECT COUNT(*) FROM vacancy WHERE id = ? AND authorId = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, vacancyId, userId);
        return count != null && count > 0;
    }


    public Long findCompanyByEmail(String email) {
        String sql= "select id from users where email = ?";
        return jdbcTemplate.queryForObject(sql, Long.class, email);
    }

    public void editVacancy(Vacancy vacancy, Long vacancyId) {
        String sqlCheckCategory = "SELECT COUNT(*) FROM category WHERE id = ?";
        Integer categoryCount = jdbcTemplate.queryForObject(sqlCheckCategory, Integer.class, vacancy.getCategoryId());

        if (categoryCount == null || categoryCount == 0) {
            throw new VacancyServiceException("Категория с таким ID не существует.");
        }

        String sql = "update vacancy set" +
                " name = ?, description = ?, categoryId = ?, salary = ?, expFrom = ?, expTo = ?, isActive = ?, updatedTime = ?" +
                " where id = ?";
        jdbcTemplate.update(
                sql,
                vacancy.getName(),
                vacancy.getDescription(),
                vacancy.getCategoryId(),
                vacancy.getSalary(),
                vacancy.getExpFrom(),
                vacancy.getExpTo(),
                vacancy.getIsActive(),
                LocalDateTime.now(),
                vacancyId
        );
    }

    public  void deleteVacancy(Long vacancyId) {
        String sql = "delete from vacancy where id = ? Limit 1";
        jdbcTemplate.update(sql, vacancyId);
    }

    public List<Vacancy> getAllVacancyIsActive() {
        String sql = "select * from vacancy where isActive = true";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
    }

    public List<Vacancy> getVacancyByCreatorId(Long authorId) {
        String sql = "select * from vacancy where authorId = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), authorId);
    }
}
