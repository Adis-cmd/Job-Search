package kg.attractor.jobsearch.exception.NumberFormatException;

import java.util.NoSuchElementException;

public class ResumeNotFoundException extends NoSuchElementException {
    public ResumeNotFoundException() {
        super(
                "Resume not found"
        );
    }
}
