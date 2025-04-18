package kg.attractor.jobsearch.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class VacancyDto {
    private Long id;
    @NotBlank(message = "Название вакансии не может быть пустым")
    @Pattern(
            regexp = "^[\\p{L}\\d .,!?—-]+$",
            message = "Допустимы буквы, цифры, пробелы и знаки .,!?—-"
    )
    @Size(min = 1, max = 100)
    private String name;
    @NotBlank(message = "Описание обязательное поле")
    @Size(min = 1, max = 255)
    @Pattern(
            regexp = "^[\\p{L}\\d .,!?—-]+$",
            message = "Допустимы буквы, цифры, пробелы и знаки .,!?—-"
    )
    private String description;
    @NotNull(message = "Нужно выбрать категорию")
    private Long categoryId;
    @NotNull(message = "Зарплата обязательна для заполнения")
    @DecimalMin(value = "100", message = "Зарплата не может быть меньше 100")
    @DecimalMax(value = "1000000000.0", message = "Максимальная зарплата: 1 000 000 000")
    private Double salary;
    @NotNull
    @Min(value = 0, message = "Опыт не может быть отрицательным")
    @Max(value = 50, message = "Максимальный опыт не может превышать 50 лет")
    private Integer expFrom;
    @NotNull
    @Min(value = 0, message = "Опыт не может быть отрицательным")
    @Max(value = 50, message = "Максимальный опыт не может превышать 50 лет")
    private Integer expTo;
    Boolean isActive = true;
    private Long authorId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedTime;

    public String getFormattedCreatedDate() {
        if (createdDate == null) return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return createdDate.format(formatter);
    }

    public String getFormattedUpdatedTime() {
        if (updatedTime == null) return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return updatedTime.format(formatter);
    }
}
