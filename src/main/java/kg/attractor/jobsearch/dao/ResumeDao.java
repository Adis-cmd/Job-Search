package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.modal.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ResumeDao {

    private final JdbcTemplate jdbcTemplate;

    public List<Resume> getResume(Long categoryId) {
        String sql = "select * from resume where categoryId = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), categoryId);
    }


    public List<Resume> getResumeByUser(Long userId) {
        String sql = "select * from resume where applicantId = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), userId);
    }
}
