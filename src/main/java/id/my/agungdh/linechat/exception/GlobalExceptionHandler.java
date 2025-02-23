package id.my.agungdh.linechat.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        // Process field errors
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(),
                        error.getDefaultMessage() != null ? error.getDefaultMessage() : "Invalid")
        );

        // Process global errors (for class-level constraints like PasswordMatches)
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            String defaultMessage = error.getDefaultMessage() != null ? error.getDefaultMessage() : "Invalid";
            // Map the password match error to the "password" key
            if ("Passwords do not match".equals(defaultMessage)) {
                errors.put("password", defaultMessage);
            } else {
                // Otherwise, use a generic key or customize as needed.
                errors.put("global", defaultMessage);
            }
        }

        return ResponseEntity.badRequest().body(errors);
    }
}
