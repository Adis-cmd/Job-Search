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
    private Integer years;
    @NotBlank(message = "{work.info.company}")
    @Size(min = 5, max = 150, message = "{work.info.companySize}")
    private String companyName;
    @NotBlank(message = "{work.info.position}")
    @Size(min = 1, max = 50, message = "{work.info.positionSize}")
    private String position;
    @NotBlank(message = "{work.info.responsibilities}")
    @Size(min = 10, max = 550, message = "{work.info.responsibilitiesSize}")
    private String responsibilities;
}
