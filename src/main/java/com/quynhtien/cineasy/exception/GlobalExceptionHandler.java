package com.quynhtien.cineasy.exception;

import com.quynhtien.cineasy.dto.response.ApiResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<ApiResponse> handleRuntimeException(RuntimeException ex) {
        log.error("Runtime Exception: " ,ex.getMessage());
        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        ApiResponse response = ApiResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(response);
    }

    @ExceptionHandler(AppException.class)
    ResponseEntity<ApiResponse> handleAppException(AppException ex) {
        ApiResponse response = ApiResponse.builder()
                .code(ex.getErrorCode().getCode())
                .message(ex.getErrorCode().getMessage())
                .build();
        return ResponseEntity.status(ex.getErrorCode().getHttpStatusCode()).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handleAppException(MethodArgumentNotValidException ex) {
        ErrorCode errorCode = ErrorCode.INVALID_REQUEST;
        ApiResponse response = ApiResponse.builder()
                .code(errorCode.getCode())
                .message(Objects.requireNonNull(ex.getFieldError()).getDefaultMessage())
                .build();
        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(response);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    ResponseEntity<ApiResponse> handleAppException(DataIntegrityViolationException ex) {
        ErrorCode errorCode = ErrorCode.INVALID_REQUEST;
        log.error("Data Exception: " ,ex.getMostSpecificCause().getMessage());
        ApiResponse response = ApiResponse.builder()
                .code(errorCode.getCode())
                .message("Data Exception: " + errorCode.getMessage())
                .build();
        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(response);
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    ResponseEntity<ApiResponse> handleAppException(AuthorizationDeniedException ex) {
        ErrorCode errorCode = ErrorCode.UNAUTHENTICATED;
        ApiResponse response = ApiResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    ResponseEntity<ApiResponse> handleAppException(HttpMessageNotReadableException ex) {
        ErrorCode errorCode = ErrorCode.PARSE_INFO_ERROR;
        log.error("Parse Exception: " + ex.getMostSpecificCause().getMessage());
        ApiResponse response = ApiResponse.builder()
                .code(errorCode.getCode())
                .message("Parse Exception: " + errorCode.getMessage())
                .build();
        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<ApiResponse> handleAppException(ConstraintViolationException ex) {
        ErrorCode errorCode = ErrorCode.INVALID_REQUEST;

        // get the first violation
        String errorMessage = ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .findFirst()
                .orElse("Validation failed");

        log.error("Constraint Violation Exception: {}", errorMessage);

        ApiResponse response = ApiResponse.builder()
                .code(errorCode.getCode())
                .message("Constraint Violation Exception: " + errorMessage)
                .build();
        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(response);
    }
}
