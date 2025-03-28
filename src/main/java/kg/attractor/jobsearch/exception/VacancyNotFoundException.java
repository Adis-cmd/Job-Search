package kg.attractor.jobsearch.exception;

import java.util.NoSuchElementException;

public class VacancyNotFoundException extends NoSuchElementException {
    public VacancyNotFoundException() {
        super("not found vacancy");
    }
}
