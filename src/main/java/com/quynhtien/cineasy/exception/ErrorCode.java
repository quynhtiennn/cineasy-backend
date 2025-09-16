package com.quynhtien.cineasy.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@AllArgsConstructor()
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
    USER_NOT_FOUND(1001, "User not found", HttpStatus.BAD_REQUEST),
    USERNAME_ALREADY_EXISTS(1002, "Username already exists", HttpStatus.BAD_REQUEST),
    INVALID_REQUEST(1003, "Invalid request", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR(1004, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);

    int code;
    String message;
    HttpStatusCode httpStatusCode;
}
