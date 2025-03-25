package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.modal.EducationInfo;
import lombok.*;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EducationInfoDao {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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
