package kg.attractor.jobsearch.exception.advice;

import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.jobsearch.service.ErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.NoSuchElementException;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {
    private final ErrorService errorService;

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException ex, Model model, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        model.addAttribute("status", status.value());
        model.addAttribute("reason", status.getReasonPhrase());
        model.addAttribute("details", request.getRequestURI());
        model.addAttribute("message", ex.getMessage());
        return "errors/error";
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException ex, Model model, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        model.addAttribute("status", status.value());
        model.addAttribute("reason", status.getReasonPhrase());
        model.addAttribute("details", request.getRequestURI());
        model.addAttribute("message", ex.getMessage());
        return "errors/error";
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElementException(NoSuchElementException ex, Model model, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        model.addAttribute("status", status.value());
        model.addAttribute("reason", status.getReasonPhrase());
        model.addAttribute("details", request.getRequestURI());
        model.addAttribute("message", ex.getMessage());
        return "errors/error";
    }

    @ExceptionHandler(NumberFormatException.class)
    public String handleNumberFormatException(NumberFormatException ex, Model model, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        model.addAttribute("status", status.value());
        model.addAttribute("reason", status.getReasonPhrase());
        model.addAttribute("details", request.getRequestURI());
        model.addAttribute("message", ex.getMessage());
        return "errors/error";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseBody> handleValidationException(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(errorService.makeResponse(ex.getBindingResult()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleMaxUploadSizeException(MaxUploadSizeExceededException ex, Model model, HttpServletRequest request) {
        HttpStatus status = HttpStatus.REQUEST_ENTITY_TOO_LARGE;
        model.addAttribute("status", status.value());
        model.addAttribute("reason", status.getReasonPhrase());
        model.addAttribute("details", request.getRequestURI());
        model.addAttribute("message", "Файл слишком большой. Максимальный размер файла: 10MB");
        return "errors/error";
    }
}
