package kg.attractor.jobsearch.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class ResumeDto {
    private Long id;
    private Long applicantId;
    @NotBlank(message = "{resume.valid.nullName}")
    @Size(min = 1, max = 100 , message = "{vacancy.valid.sizeRange}")
    @Pattern(
            regexp = "^[\\p{L}\\d .,!?—-]+$",
            message = "{resume.valid.patternName}"
    )
    private String name;
    @NotNull(message = "{resume.valid.nullCategory}")
    private Long categoryId;
    @NotNull(message = "{resume.valid.nullSalary}")
    @DecimalMin(value = "100", message = "{resume.valid.salaryMin}")
    @DecimalMax(value = "1000000000.0", message = "{resume.valid.salaryMax}")
    private Double salary;
    @NotNull(message = "{resume.valid.nullIsActive}")
    private Boolean isActive = true;
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;
    private List<WorkExperienceInfoDto> workExperiences;
    private List<EducationInfoDto> educationInfos;
    private List<ContactInfoDto> contactInfos;


    public String getFormattedCreatedDate() {
        if (createdDate == null) return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return createdDate.format(formatter);
    }

    public String getFormattedUpdatedTime() {
        if (updateTime == null) return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return updateTime.format(formatter);
    }
}
