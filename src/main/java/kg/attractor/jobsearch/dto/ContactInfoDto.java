package kg.attractor.jobsearch.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactInfoDto {
    private Long id;

    @NotNull(message = "Поле не может быть пустым")
    private Long typeId;

    private Long resumeId;

    @NotNull(message = "Поле не может быть пустым")
    @Size(min = 5, max = 100, message = "Value must be between 5 and 100 characters")
    @Pattern(regexp = "^(\\+?[0-9]{1,4}[\\s-]?)?([0-9]{1,3}[\\s-]?)?([0-9]{1,4}[\\s-]?)?([0-9]{1,4}[\\s-]?)$|^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", message = "Invalid format, should be either a valid phone number or email.")
    private String value;
}
