package kg.attractor.jobsearch.exception;

import java.util.NoSuchElementException;

public class WorkExperienceInfoException extends NoSuchElementException {
    public WorkExperienceInfoException() {
        super("Not found work Experience Info");
    }
}
