package com.gicamube.e_commerce.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntime(RuntimeException ex) {
        return ResponseEntity.badRequest().body(
                new ErrorResponse(ex.getMessage(), LocalDateTime.now())
        );
    }

    public record ErrorResponse(String message, LocalDateTime timestamp) {}
}
