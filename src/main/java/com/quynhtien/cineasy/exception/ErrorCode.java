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
    //generic error codes 1001 - 1999
    INTERNAL_SERVER_ERROR(1001, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR),

    //user error codes 2001 - 2999
    USER_NOT_FOUND(2001, "User not found", HttpStatus.BAD_REQUEST),
    INVALID_USER_INFO(2002, "Invalid user information", HttpStatus.BAD_REQUEST),
    INVALID_REQUEST(2003, "Invalid request", HttpStatus.BAD_REQUEST),

    //authentication error codes 3001 - 3999
    GENERATE_TOKEN_ERROR(3001, "Error generating token", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_TOKEN(3002, "Invalid token", HttpStatus.UNAUTHORIZED);


    int code;
    String message;
    HttpStatusCode httpStatusCode;
}
