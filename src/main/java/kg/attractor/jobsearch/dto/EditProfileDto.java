package kg.attractor.jobsearch.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditProfileDto {

    private Long id;

    @NotBlank(message = "Поле имени не может быть пустым")
    private String name;

    private String surname;

    @NotNull(message = "Данное поле не может быть пустым")
    private Integer age;

    private String avatar;

}
