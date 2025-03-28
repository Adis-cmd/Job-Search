package kg.attractor.jobsearch.exception;

import java.util.NoSuchElementException;

public class EducationInfoException extends NoSuchElementException {
    public EducationInfoException() {
        super("Not found Education Info");
    }
}
