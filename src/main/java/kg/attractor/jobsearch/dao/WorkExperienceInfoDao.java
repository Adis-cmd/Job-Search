package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.modal.WorkExperienceInfo;
import lombok.*;
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
public class WorkExperienceInfoDao {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;
    public List<WorkExperienceInfo> getAllWorkExperienceInfo(){
        String sql = "Select * from work_experience_info";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(WorkExperienceInfo.class));
    }

    public Optional<WorkExperienceInfo> getWorkExperienceInfoById(Long workId){
        String sql = "select * from work_experience_info where id = ?";
        return  Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(WorkExperienceInfo.class), workId)
                )
        );

    }

    public void createWorkExperienceInfo(WorkExperienceInfo workExperienceInfo) {
        String sql = "insert into work_experience_info " +
                "(resumeId, years, companyName, position, responsibilities)" +
                "values (:resumeId, :years, :companyName, :position, :responsibilities)";

        namedParameterJdbcTemplate.update(
                sql,
                new MapSqlParameterSource()
                        .addValue("resumeId", workExperienceInfo.getResumeId())
                        .addValue("years", workExperienceInfo.getYears())
                        .addValue("companyName", workExperienceInfo.getCompanyName())
                        .addValue("position", workExperienceInfo.getPosition())
                        .addValue("responsibilities", workExperienceInfo.getResponsibilities())
        );
    }
}
