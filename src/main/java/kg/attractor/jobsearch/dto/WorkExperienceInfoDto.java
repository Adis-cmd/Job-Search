package kg.attractor.jobsearch.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class WorkExperienceInfoDto {
    private Long id;
    private Long resumeId;
    @NotNull(message = "Робочий опыт не должен быть пустым")
    @Min(value = 0,  message = "Опыт работы не может быть отрицательным")
    @Max(value = 50, message = "Опыт работы не может превышать 50 лет")
    private Integer years;
    @NotBlank(message = "Название компании обязательно")
    @Size(min = 5, max = 150, message = "Название компании должно быть от 5 до 150 символов")
    private String companyName;
    @NotBlank(message = "Должность обязательна")
    @Size(min = 1, max = 50, message = "Должность должна быть от 2 до 50 символов")
    private String position;
    @NotBlank(message = "Обязанности должны быть заполнены")
    @Size(min = 10, max = 550, message = "Описание обязанностей должно быть от 10 до 550 символов")
    private String responsibilities;
}
