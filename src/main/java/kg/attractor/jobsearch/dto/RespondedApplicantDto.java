package kg.attractor.jobsearch.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class RespondedApplicantDto {
    private Long id;
    private Long resumeId;
    private Long vacancyId;
    private Boolean confirmation;
}
