package kg.attractor.jobsearch.anotation.valid;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import kg.attractor.jobsearch.anotation.ExperienceRangeValid;
import kg.attractor.jobsearch.dto.VacancyDto;

public class ExperienceRangeValidator implements ConstraintValidator<ExperienceRangeValid, VacancyDto> {

    @Override
    public boolean isValid(VacancyDto dto, ConstraintValidatorContext context) {
        Integer from = dto.getExpFrom();
        Integer to = dto.getExpTo();

        if (from == null || to == null) {
            return true;
        }

        boolean valid = true;



        if (from < 0) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Начальный опыт не может быть отрицательным")
                    .addPropertyNode("expFrom").addConstraintViolation();
            valid = false;
        }

        if (to > 50) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Конечный опыт не может быть больше 70")
                    .addPropertyNode("expTo").addConstraintViolation();
            valid = false;
        }

        if (to < 0) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Конечный опыт не может быть отрицательным")
                    .addPropertyNode("expTo").addConstraintViolation();
            valid = false;
        }

        if (from > to) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Начальный опыт не может быть больше конечного")
                    .addPropertyNode("expFrom").addConstraintViolation();
            valid = false;
        }

        return valid;
    }
}
