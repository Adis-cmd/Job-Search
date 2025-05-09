package kg.attractor.jobsearch.dto;

import jakarta.validation.constraints.*;
import kg.attractor.jobsearch.anotation.ExperienceRangeValid;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@ExperienceRangeValid
public class VacancyDto {
    private Long id;
    @NotBlank(message = "{vacancy.valid.nullName}")
    @Pattern(
            regexp = "^[\\p{L}\\d .,!?—-]+$",
            message = "{resume.valid.patternName}"
    )
    @Size(min = 1, max = 100, message = "{vacancy.valid.sizeRange}")
    private String name;
    @NotBlank(message = "{vacancy.valid.nullDescription}")
    @Size(min = 1, max = 255 , message = "{vacancy.valid.sizeRange}")
    @Pattern(
            regexp = "^[\\p{L}\\d .,!?—-]+$",
            message = "{resume.valid.patternName}"
    )
    private String description;
    @NotNull(message = "{vacancy.valid.nullCategory}")
    private Long categoryId;
    @NotNull(message = "{vacancy.valid.nullSalary}")
    @DecimalMin(value = "100", message = "{resume.valid.salaryMin}")
    @DecimalMax(value = "1000000000.0", message = "{resume.valid.salaryMax}")
    private Double salary;
    @NotNull(message = "{vacancy.valid.nullExpFrom}")
    private Integer expFrom;
    @NotNull(message = "{vacancy.valid.nullExpTo}")
    private Integer expTo;
    Boolean isActive = true;
    private Long authorId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedTime;
    private Long responseCount;


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
