package com.nicholasashenfelter.DiscreteJava.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {
        // Extract the first error message (or customize further if needed)
        String errorMessage = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", ZonedDateTime.now());
        body.put("error", errorMessage);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, Object>> handleConstraintViolation(ConstraintViolationException ex) {
        String errorMessage = ex.getConstraintViolations()
                .stream()
                .map(cv -> cv.getMessage())
                .collect(Collectors.joining(", "));

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", ZonedDateTime.now());
        body.put("error", errorMessage);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

}