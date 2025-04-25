package kg.attractor.jobsearch.exception;

import java.util.NoSuchElementException;

public class AccountTypeException extends NoSuchElementException {
  public AccountTypeException(String message) {
    super(message);
  }
}
