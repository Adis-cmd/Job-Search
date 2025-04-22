package kg.attractor.jobsearch.anotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import kg.attractor.jobsearch.anotation.valid.ExperienceRangeValidator;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExperienceRangeValidator.class)
@Documented
public @interface ExperienceRangeValid {
    String message() default "Неверно указан диапазон опыта";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
