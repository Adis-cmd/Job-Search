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
    @Pattern(regexp = "^[\\\\p{L} ]+$", message = "Имя должно содержать только буквы и пробелы")
    private String name;
    @NotBlank(message = "Фамилия обязательна")
    @Pattern(regexp = "^[\\\\p{L} ]+$", message = "Фамилия должна содержать только буквы и пробелы")
    private String surname;
    @NotBlank(message = "Возраст обязателен")
    @Min(value = 16)
    @Max(value = 100)
    private Integer age;
    @NotBlank(message = "Email не может быть пустым")
    @Email(message = "Введите действительный email")
    private String email;
    @NotBlank
    @Size(min = 4, max = 120, message = "Пароль должен состоять из 4–120 строк.")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z])", message = """
             Пароль должен содержать:
                     • 1 цифру (0-9)
                     • 1 строчную букву (a-z)
                     • 1 заглавную букву (A-Z)
            """)
    private String password;
    @NotBlank
    @Pattern(regexp = "^(\\\\+\\\\d{10,}|\\\\d{10,})$", message = """
            Номер должен:
                     • Начинаться с '+' и содержать 10+ цифр ИЛИ
                     • Содержать 10+ цифр без других символов
                     Примеры: +71234567890 или 88555555555
            """)
    private String phoneNumber;
    private String avatar;
    @NotBlank
    private String accountType;
}
