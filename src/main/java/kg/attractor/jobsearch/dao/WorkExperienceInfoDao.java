package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.modal.WorkExperienceInfo;
import lombok.*;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkExperienceInfoDao {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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
