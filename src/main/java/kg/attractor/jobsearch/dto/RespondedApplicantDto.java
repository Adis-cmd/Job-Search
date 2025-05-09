package kg.attractor.jobsearch.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class RespondedApplicantDto {
    private Long id;
    private ResumeDto resumeId;
    private VacancyDto vacancyId;
    private Boolean confirmation;
}
