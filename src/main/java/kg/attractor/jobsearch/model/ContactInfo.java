package kg.attractor.jobsearch.model;

import lombok.Data;

@Data
public class ContactInfo {
    private Long id;
    private Long typeId;
    private Long resumeId;
    private String value;
}
