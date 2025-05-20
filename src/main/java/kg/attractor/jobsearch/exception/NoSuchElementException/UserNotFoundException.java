package kg.attractor.jobsearch.exception.NoSuchElementException;

import java.util.NoSuchElementException;

public class UserNotFoundException extends NoSuchElementException {
  public UserNotFoundException() {
    super(
            "User not found"
    );
  }

  public UserNotFoundException(String message) {
    super(message);
  }
}
