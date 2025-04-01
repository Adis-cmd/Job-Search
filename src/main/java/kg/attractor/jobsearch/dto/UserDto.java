package kg.attractor.jobsearch.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class UserDto {
    private Long id;
    @NotBlank(message = "Имя обязательно")
    @Pattern(regexp = "^[\\p{L} ]+$", message = "Имя должно содержать только буквы и пробелы")
    private String name;
    @NotBlank(message = "Фамилия обязательна")
    @Pattern(regexp = "^[\\p{L} ]+$", message = "Фамилия должна содержать только буквы и пробелы")
    private String surname;
    @NotNull(message = "Возраст обязателен")
    @Min(value = 16)
    @Max(value = 100)
    private Integer age;
    @NotBlank(message = "Email не может быть пустым")
    @Email(message = "Введите действительный email")
    private String email;
    @NotBlank(message = "Пароль не должен быть пустым")
    @Size(min = 4, max = 120, message = "Пароль должен состоять из 4–120 строк.")
    private String password;
    @NotBlank
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Неверный формат телефонного номера")
    private String phoneNumber;
    private String avatar;
    @NotNull
    private Long accountType;
    private Boolean enabled = true;
}
