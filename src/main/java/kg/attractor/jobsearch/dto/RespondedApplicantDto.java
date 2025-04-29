package kg.attractor.jobsearch.dto;

import jakarta.validation.constraints.NotNull;
import kg.attractor.jobsearch.model.Resume;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class RespondedApplicantDto {
    private Long id;
    @NotNull(message = "ID резюме обязательна")
    private ResumeDto resumeId;
    @NotNull(message = "ID вакансии обязательна")
    private VacancyDto vacancyId;
    @NotNull(message = "Статус подтверждения должен быть указан")
    private Boolean confirmation;
}
