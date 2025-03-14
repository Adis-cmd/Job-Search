package kg.attractor.jobsearch.modal;

import lombok.Data;

@Data
public class RespondedApplicants {
    private int id;
    private int resumeId;
    private int vacancyId;
    private boolean confirmation;
}
