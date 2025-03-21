package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.modal.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDao {
    private final JdbcTemplate jdbcTemplate;


    public List<User> getAllUserName(String name) {
        String sql = "select * from users where name like ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), "%" + name + "%");
    }

    public Optional<User> getUserEmail(String email) {
        String sql = "select * from users where email like ?";
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), "%" + email + "%")
                )
        );
    }


    public Optional<User> getUserPhone(String phone) {
        String sql = "select * from users where phoneNumber like ?";
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), "%" + phone + "%")
                )
        );
    }


    public boolean userExists(String email) {
        String sql = "select count(*) from users where email = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }

    public List<User> getUserByResponse() {
        String sql = """
                SELECT u.*,\
                 r.*,\
                 a.*,\
                 v.*
                from users u
                left join resume r ON r.applicantId = u.id
                left join responded_applicant a ON r.id = a.resumeId
                left join vacancy v ON a.vacancyId = v.id
                where a.confirmation = true;
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }
}
