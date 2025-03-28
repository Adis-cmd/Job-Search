package kg.attractor.jobsearch.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class ResumeDto {
    private Long id;
    private Long applicantId;
    @NotBlank(message = "Название резюме не может быть пустым")
    @Size(min = 1, max = 100)
    @Pattern(
            regexp = "^[\\p{L}\\d .,!?—-]+$",
            message = "Допустимы буквы, цифры, пробелы и знаки .,!?—-"
    )
    private String name;
    @NotNull(message = "Категория не может быть пустым")
    private Long categoryId;
    @NotNull(message = "Зарплата обязательна для заполнения")
    @DecimalMin(value = "100", message = "Зарплата не может быть меньше 100")
    @DecimalMax(value = "1000000000.0", message = "Максимальная зарплата: 1 000 000 000")
    private Double salary;
    @NotNull(message = "Статус активности должен быть указан")
    private Boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;
    private List<WorkExperienceInfoDto> workExperiences;
    private List<EducationInfoDto> educationInfos;
}
