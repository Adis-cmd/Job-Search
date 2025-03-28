package kg.attractor.jobsearch.exception;

public class IdFormatCheckException extends NumberFormatException {
    public IdFormatCheckException() {
        super("The ID must be a numeric value.");
    }
}
