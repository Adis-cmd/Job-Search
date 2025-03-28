package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.modal.EducationInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EducationInfoDao {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;


    public List<EducationInfo> getAllEducationInfo() {
        String sql = "select * from education_info";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(EducationInfo.class));
    }

    public Optional<EducationInfo> getEducationInfoById(Long eduId) {
        String sql = "select * from  education_info where id = ?";

        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(EducationInfo.class), eduId)
                )
        );
    }


    public void createEducationInfo(EducationInfo educationInfo) {
        String sql = """
                insert into education_info 
                                (resumeId, institution, program, startDate, endDate, degree) 
                                 values (:resumeId, :institution, :program, :startDate, :endDate, :degree)
                """;

        namedParameterJdbcTemplate.update(
                sql, new MapSqlParameterSource()
                        .addValue("resumeId", educationInfo.getResumeId())
                        .addValue("institution", educationInfo.getInstitution())
                        .addValue("program", educationInfo.getProgram())
                        .addValue("startDate", educationInfo.getStartDate())
                        .addValue("endDate", educationInfo.getEndDate())
                        .addValue("degree", educationInfo.getDegree())
        );
    }
}
