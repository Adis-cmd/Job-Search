package kg.attractor.jobsearch.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class RespondedApplicantDto {
    private Long id;
    @NotNull(message = "ID резюме обязательна")
    private Long resumeId;
    @NotNull(message = "ID вакансии обязательна")
    private Long vacancyId;
    @NotNull(message = "Статус подтверждения должен быть указан")
    private Boolean confirmation;
}
