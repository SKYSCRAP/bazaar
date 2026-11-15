package com.shopzone;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Dtos.ApiError> handleRuntime(RuntimeException ex) {
        if (ex.getMessage().contains("exists")) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new Dtos.ApiError("CONFLICT", ex.getMessage()));
        }
        
        if (ex.getMessage().contains("Invalid")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new Dtos.ApiError("UNAUTHORIZED", ex.getMessage()));
        }
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new Dtos.ApiError("ERROR", ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Dtos.ApiError> handleValidation(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body(new Dtos.ApiError("VALIDATION_ERROR", "Invalid input"));
    }
}