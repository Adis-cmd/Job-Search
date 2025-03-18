package kg.attractor.jobsearch.modal;

import lombok.Data;

@Data
public class RespondedApplicant {
    private Integer id;
    private Integer resumeId;
    private Integer vacancyId;
    private boolean confirmation;
}
