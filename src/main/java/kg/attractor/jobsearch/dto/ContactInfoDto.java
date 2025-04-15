package kg.attractor.jobsearch.dto;

import lombok.Data;

@Data
public class ContactInfoDto {
    private Long id;
    private Long typeId;
    private Long resumeId;
    private String value;
}
