package kg.attractor.jobsearch.exception.NoSuchElementException;

import java.util.NoSuchElementException;

public class VacancyNotFoundException extends NoSuchElementException {
    public VacancyNotFoundException() {
        super("not found vacancy");
    }

    public VacancyNotFoundException(String message) {
        super(message);
    }
}
