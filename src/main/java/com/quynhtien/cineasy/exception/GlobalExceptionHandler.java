package com.quynhtien.cineasy.exception;

import com.quynhtien.cineasy.dto.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
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
        ApiResponse response = ApiResponse.builder()
                .code(errorCode.getCode())
                .message("Data Exception: " + ex.getMostSpecificCause().getMessage())
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
}
