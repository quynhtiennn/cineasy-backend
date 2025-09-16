package com.quynhtien.cineasy.exception;

import com.quynhtien.cineasy.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<ApiResponse> handleRuntimeException(RuntimeException ex) {
        // Log the exception (you can use a logging framework here)
        System.err.println("An error occurred: " + ex.getMessage());
        // Return a generic error message
        return "An internal error occurred. Please try again later.";
    }
}
