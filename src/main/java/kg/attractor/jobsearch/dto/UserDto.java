package kg.attractor.jobsearch.dto;

import jakarta.validation.constraints.*;
import kg.attractor.jobsearch.anotation.UniqueEmail;
import kg.attractor.jobsearch.anotation.UniquePhoneNumber;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class UserDto {
    private Long id;
    @NotBlank(message = "{register.valid.nullName}")
    @Pattern(regexp = "^[\\p{L} ]+$", message = "{register.valid.patternName}")
    private String name;
    @NotBlank(message = "{register.valid.nullSurname}")
    @Pattern(regexp = "^[\\p{L} ]+$", message = "{register.valid.patternSurname}")
    private String surname;
    @NotNull(message = "{register.valid.nullAge}")
    @Min(value = 16)
    @Max(value = 100)
    private Integer age;
    @NotBlank(message = "{register.valid.nullEmail}")
    @Email(message = "{register.valid.email}")
    @UniqueEmail
    private String email;
    @NotBlank(message = "{register.valid.nullPassword}")
    @Size(min = 4, max = 120, message = "{register.valid.password}")
    private String password;
    @NotBlank(message = "{register.valid.nullPhoneNumber}")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "{register.valid.patternPhoneNumber}")
    @UniquePhoneNumber
    private String phoneNumber;
    private String avatar;
    private Long accountType;
    private Boolean enabled = true;
    private String language;
}
