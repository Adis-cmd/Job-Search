package kg.attractor.jobsearch.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditProfileDto {

    private Long id;

    @NotBlank(message = "{edit.profile.name}")
    private String name;

    @NotBlank(message = "{edit.profile.surname}")
    private String surname;

    @NotNull(message = "{edit.profile.age}")
    @Min(value = 18, message = "{edit.profile.ageMin}")
    @Max(value = 100, message = "{edit.profile.ageMax}")
    private Integer age;

    private String avatar;

}
