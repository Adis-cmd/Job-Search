package kg.attractor.jobsearch.exception.advice;

import kg.attractor.jobsearch.service.ErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {
    private final ErrorService errorService;

    @ExceptionHandler(IllegalArgumentException.class)
    private ErrorResponseBody  illegalArgumentExceptionHandler(IllegalArgumentException e) {
        return errorService.makeResponse(e);
    }

    @ExceptionHandler(NoSuchElementException.class)
    private ErrorResponseBody noSuchElementException(NoSuchElementException ex) {
        return errorService.makeResponse(ex);
    }

    @ExceptionHandler(NumberFormatException.class)
    private ErrorResponseBody numberFormatException(NumberFormatException ex) {
        return errorService.makeResponse(ex);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseBody> validationHandler(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(errorService.makeResponse(ex.getBindingResult()), HttpStatus.BAD_REQUEST);
    }
}
