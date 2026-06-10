package com.increff.pos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Catches explicit business rule violations (e.g., "Client already exists")
     * Maps them to an appropriate HTTP 400 Bad Request instead of a generic 500
     * crash.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleBusinessExceptions(IllegalArgumentException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", ex.getMessage());
        errorMap.put("type", "BusinessRuleViolation");
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }

    /**
     * Catches Spring input field validation failures (e.g., blank names).
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        String defaultMessage = ex.getBindingResult().getFieldError().getDefaultMessage();
        errorMap.put("error", defaultMessage);
        errorMap.put("type", "ValidationError");
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }

    /**
     * Catch-all fallback for actual structural system drops (e.g., broken database
     * connection).
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleAllExceptions(Exception ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", ex.getMessage());
        errorMap.put("type", ex.getClass().getSimpleName());
        return new ResponseEntity<>(errorMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}