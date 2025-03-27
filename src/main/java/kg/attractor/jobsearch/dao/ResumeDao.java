package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.modal.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ResumeDao {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final KeyHolder keyHolder = new GeneratedKeyHolder();


    public List<Resume> getAllResumes() {
        String sql = "select * from resume";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class));
    }

    public List<Resume> getResume(Long categoryId) {
        String sql = "select * from resume where categoryId = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), categoryId);
    }


    public List<Resume> getResumeByUser(Long userId) {
        String sql = "select * from resume where applicantId = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), userId);
    }

    public Optional<Resume> getResumeById(Long resumeId) {
        String sql = "select * from resume where id = ?";

        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), resumeId)
                )
        );

    }

    public void editResume(Resume resume, Long resumeId) {
        String sql = "update resume set name = ?, categoryId = ?, salary = ?, isActive = ?, updateTime = ? " +
                "where id = ?";

        jdbcTemplate.update(sql,
                resume.getName(),
                resume.getCategoryId(),
                resume.getSalary(),
                resume.getIsActive(),
                LocalDateTime.now(),
                resumeId
        );
    }

    public void deleteResume(Long resumeId) {
        String sql = "delete from resume where id = ? limit 1";

        jdbcTemplate.update(sql, resumeId);
    }


    public Long createResumes(Resume resume, Long userId) {
        String sql = "insert into resume " +
                "(applicantId, name, categoryId, salary, isActive , createdDate, updateTime)" +
                " values (:applicantId, :name, :categoryId, :salary, :isActive, :createdDate, :updateTime)";

         namedParameterJdbcTemplate.update(
                sql,
                new MapSqlParameterSource()
                        .addValue("applicantId", userId)
                        .addValue("name", resume.getName())
                        .addValue("categoryId", resume.getCategoryId())
                        .addValue("salary", resume.getSalary())
                        .addValue("isActive", resume.getIsActive())
                        .addValue("createdDate", LocalDateTime.now())
                        .addValue("updateTime", LocalDateTime.now()),
                 keyHolder,
                 new String[]{"id"}

        );
        Long generatedId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        resume.setId(generatedId);

        return generatedId;
    }
}
