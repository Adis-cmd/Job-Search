package kg.attractor.jobsearch.dto;

import jakarta.validation.constraints.*;
import lombok.*;



@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {
    @NotBlank(message = "Название компании обязательно")
    @Size(min = 3, max = 100, message = "Название компании должно быть от 3 до 100 символов")
    private String name;

    @NotBlank(message = "Email обязателен")
    @Email(message = "Введите корректный формат почты")
    private String email;

    @NotNull(message = "Пароль не может быть пустым")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).+$",
            message = "Пароль должен содержать х    отя бы одну заглавную букву и одну цифру")
    @Size(min = 8, message = "Пароль должен быть не менее 8 символов")
    private String password;

    @NotBlank(message = "Номер телефона обязателен")
    @Pattern(regexp = "^996\\d{9}$", message = "Номер телефона должен быть в формате 996XXXXXXXXX (12 цифр)")
    private String phoneNumber;

    private Long accountTypeId;
}