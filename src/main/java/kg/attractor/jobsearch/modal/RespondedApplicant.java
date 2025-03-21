package kg.attractor.jobsearch.modal;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class RespondedApplicant {
    private Long id;
    private Long resumeId;
    private Long vacancyId;
    private Boolean confirmation;
}
