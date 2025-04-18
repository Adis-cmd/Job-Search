package kg.attractor.jobsearch.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ContactInfoDto {
    private Long id;

    @NotNull(message = "Поле не может быть пустым")
    private ContactTypeDto typeId;

    @NotNull(message = "Поле не может быть пустым")
    private ResumeDto resumeId;

    @NotNull(message = "Поле не может быть пустым")
    @Size(min = 5, max = 100, message = "Value must be between 5 and 100 characters")
    @Pattern(regexp = "^(.+)@(.+)$", message = "Invalid email format")
    private String value;
}
