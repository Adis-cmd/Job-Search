package kg.attractor.jobsearch.exception;

import java.util.NoSuchElementException;

public class VacancyServiceException extends NumberFormatException {
  public VacancyServiceException(String message) {
    super(message);
  }
}
