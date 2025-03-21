package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.modal.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class VacancyDao {

    private final JdbcTemplate jdbcTemplate;


    public List<Vacancy> getAllVacancies() {
        String sql = "select * from vacancy";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
    }

    public List<Vacancy> getVacanciesByCategory(Long categoryId) {
        String sql = "select * from vacancy where categoryId = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), categoryId);
    }

    public List<Vacancy> getVacancyByApplicant() {
        String sql = "select * from vacancy " +
                "Left join responded_applicant a on vacancy.id = a.vacancyId" +
                " where vacancy.id = a.vacancyId";
        return  jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
    }
}
