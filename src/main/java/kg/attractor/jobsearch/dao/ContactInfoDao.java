package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.model.ContactInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContactInfoDao {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public void addContactInfo(ContactInfo contactInfo) {
        String sql = "insert into contact_info (type_id, resumeId, info_value) " +
                "values (:typeId, :resumeId, :infoValue)";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("typeId", contactInfo.getTypeId())
                .addValue("resumeId", contactInfo.getResumeId())
                .addValue("infoValue", contactInfo.getValue());

        namedParameterJdbcTemplate.update(sql, params);

    }
}
