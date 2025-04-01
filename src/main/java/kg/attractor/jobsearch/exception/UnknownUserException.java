package kg.attractor.jobsearch.exception;

public class UnknownUserException extends IllegalArgumentException  {
    public UnknownUserException(String message) {
        super(message);
    }
}
