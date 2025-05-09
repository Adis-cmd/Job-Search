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

    private Long typeId;

    private Long resumeId;

    @NotNull(message = "{contact.info.valueNull}")
    @Size(min = 5, max = 100, message = "{contact.info.valueSize}")
    @Pattern(regexp = "^(\\+?[0-9]{1,4}[\\s-]?)?([0-9]{1,3}[\\s-]?)?([0-9]{1,4}[\\s-]?)?([0-9]{1,4}[\\s-]?)$|^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", message = "{contact.info.valuePattern}")
    private String value;
}
