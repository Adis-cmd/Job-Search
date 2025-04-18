package kg.attractor.jobsearch.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class EducationInfoDto {
    private Long id;
    private ResumeDto resumeId;
    @NotBlank(message = "Учебное заведение обязательно для заполнения")
    @Size(max = 100, message = "Название учебного заведения не должно превышать 100 символов")
    private String institution;
    @NotBlank(message = "Название программы обязательно")
    @Size(max = 100, message = "Название программы не должно превышать 100 символов")
    private String program;
    @NotNull(message = "Дата начала обучения обязательна")
    @PastOrPresent(message = "Дата начала должна быть в прошлом или настоящем")
    private LocalDate startDate;
    @PastOrPresent(message = "Дата окончания должна быть в прошлом или настоящем")
    private LocalDate endDate;
    @NotBlank(message = "Степень обязательна")
    @Size(max = 50, message = "Название степени не должно превышать 50 символов")
    private String degree;
}
