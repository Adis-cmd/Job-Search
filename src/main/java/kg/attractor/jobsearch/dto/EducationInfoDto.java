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
    private Long resumeId;
    @NotBlank(message = "{education.info.nullInstitution}")
    @Size(max = 100, message = "{education.info.nullInstitutionSize}")
    private String institution;
    @NotBlank(message = "{education.info.nullProgram}")
    @Size(max = 100, message = "{education.info.nullProgramSize}")
    private String program;
    @NotNull(message = "{education.info.nullStartDate}")
    @PastOrPresent(message = "{education.info.startDatePresent}")
    private LocalDate startDate;
    @PastOrPresent(message = "{education.info.endDatePresent}")
    private LocalDate endDate;
    @NotBlank(message = "{education.info.nullDegree}")
    @Size(max = 50, message = "{education.info.nullDegreeSize}")
    private String degree;
}
