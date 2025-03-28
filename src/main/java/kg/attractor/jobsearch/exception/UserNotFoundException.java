package kg.attractor.jobsearch.exception;

import java.util.NoSuchElementException;

public class UserNotFoundException extends NoSuchElementException {
  public UserNotFoundException() {
    super(
            "User not found"
    );
  }
}
