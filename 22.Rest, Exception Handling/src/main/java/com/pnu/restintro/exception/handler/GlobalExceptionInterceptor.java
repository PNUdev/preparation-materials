package com.pnu.restintro.exception.handler;

import com.pnu.restintro.exception.EntityDuplicationException;
import com.pnu.restintro.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionInterceptor {

    private final static String ERROR = "error";

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<Map<String, String>> handleEntityNotFoundException(Exception e) {
        log.warn(e.getMessage(), e);
        Map<String, String> errors = new HashMap<>();
        errors.put(ERROR, e.getLocalizedMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errors);
    }

    @ExceptionHandler({EntityDuplicationException.class, IllegalArgumentException.class})
    public ResponseEntity<Map<String, String>> handleEntityDuplicateException(Exception e) {
        log.warn(e.getMessage(), e);
        Map<String, String> errors = new HashMap<>();
        errors.put(ERROR, e.getLocalizedMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error-> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Map<String, String>> handleInternalError(Exception e) {
        log.warn(e.getMessage(), e);
        Map<String, String> errors = new HashMap<>();
        errors.put(ERROR, e.getLocalizedMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errors);
    }
}
