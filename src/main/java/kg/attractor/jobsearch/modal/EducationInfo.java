package kg.attractor.jobsearch.modal;

import lombok.Data;

import java.time.LocalDate;
@Data
public class EducationInfo {
    private Integer id;
    private Integer resumeId;
    private String institution;
    private String program;
    private LocalDate startDate;
    private LocalDate endDate;
    private String degree;
}
