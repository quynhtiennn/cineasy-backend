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
    PARSE_INFO_ERROR(1002, "Cannot parse the information", HttpStatus.BAD_REQUEST),

    //user error codes 2001 - 2999
    USER_NOT_FOUND(2001, "User not found", HttpStatus.BAD_REQUEST),
    INVALID_USER_INFO(2002, "Invalid user information", HttpStatus.BAD_REQUEST),
    INVALID_REQUEST(2003, "Invalid request", HttpStatus.BAD_REQUEST),

    //authentication error codes 3001 - 3999
    GENERATE_TOKEN_ERROR(3001, "Error generating token", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_TOKEN(3002, "Invalid token", HttpStatus.UNAUTHORIZED),
    UNAUTHENTICATED(3003, "Unauthenticated request", HttpStatus.UNAUTHORIZED),
    UNAUTHORISED(3004, "Unauthorised request", HttpStatus.FORBIDDEN),

    //role error codes 4001 - 4999
    ROLE_NOT_FOUND(4001, "Role not found", HttpStatus.BAD_REQUEST),

    //permission error codes 5001 - 5999
    PERMISSION_NOT_FOUND(5001, "Permission not found", HttpStatus.BAD_REQUEST),

    //movie error codes 6001 - 6999
    MOVIE_NOT_FOUND(6001, "Movie not found", HttpStatus.BAD_REQUEST),

    //auditorium error codes 7001 - 7999
    AUDITORIUM_NOT_FOUND(7001, "Auditorium not found", HttpStatus.BAD_REQUEST),

    //seat error codes 8001 - 8999
    SEAT_NOT_FOUND(8001, "Seat not found", HttpStatus.BAD_REQUEST),

    //showtime error codes 9001 - 9999
    SHOWTIME_NOT_FOUND(9001, "Show time not found", HttpStatus.BAD_REQUEST),

    //ticket error codes 10001 - 10999
    TICKET_NOT_FOUND(10001, "Ticket not found", HttpStatus.BAD_REQUEST),
    TICKET_NOT_AVAILABLE(10002, "Ticket already booked", HttpStatus.BAD_REQUEST),

    //booking error codes 11001 - 11999
    BOOKING_NOT_FOUND(11001, "Booking not found", HttpStatus.BAD_REQUEST),
    BOOKING_ALREADY_CANCELLED(11002, "Booking already cancelled", HttpStatus.BAD_REQUEST),
    BOOKING_CANNOT_BE_SET(11003, "Booking cannot be set", HttpStatus.BAD_REQUEST),
    BOOKING_STATUS_INVALID(11004, "Booking cannot be set", HttpStatus.BAD_REQUEST),

    //payment error codes 12001 - 12999
    PAYMENT_NOT_FOUND(12001, "Payment not found", HttpStatus.BAD_REQUEST),
    PAYMENT_CANNOT_BE_SET(12002, "Payment cannot be set", HttpStatus.BAD_REQUEST),

    //file error codes 13001 - 13999
    FILE_NOT_FOUND(13001, "File not found", HttpStatus.BAD_REQUEST),

    //Email token error codes 14001 - 14999
    INVALID_EMAIL_VERIFICATION(14001, "Email verification failed", HttpStatus.BAD_REQUEST)
    ;
    int code;
    String message;
    HttpStatusCode httpStatusCode;
}
