package kg.attractor.jobsearch.modal;

import lombok.Data;

@Data
public class WorkExperienceInfo {
    private int id;
    private int resumeId;
    private int years;
    private String companyName;
    private String position;
    private String responsibilities;
}
