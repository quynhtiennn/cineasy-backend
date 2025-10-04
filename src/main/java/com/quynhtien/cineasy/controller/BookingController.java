package com.quynhtien.cineasy.controller;

import com.quynhtien.cineasy.dto.request.BookingRequest;
import com.quynhtien.cineasy.dto.response.ApiResponse;
import com.quynhtien.cineasy.dto.response.BookingResponse;
import com.quynhtien.cineasy.service.BookingService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping("/bookings")
public class BookingController {
    BookingService bookingService;

    //Get all bookings
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ApiResponse<List<BookingResponse>> findAll() {
        return ApiResponse.<List<BookingResponse>>builder()
                .result(bookingService.getBookings())
                .build();
    }

    //Get booking by id
    @GetMapping("/{id}")
    public ApiResponse<BookingResponse> findAll(@PathVariable String id) {
        return ApiResponse.<BookingResponse>builder()
                .result(bookingService.getBooking(id))
                .build();
    }

    //Create booking
    @PostMapping
    public ApiResponse<BookingResponse> createBooking(@RequestBody @Valid BookingRequest request) {
        return ApiResponse.<BookingResponse>builder()
                .result(bookingService.createBooking(request))
                .build();
    }

    //Update booking
    /*@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ApiResponse<BookingResponse> updateBooking(@PathVariable String id, @RequestBody @Valid BookingRequest request) {
        return ApiResponse.<BookingResponse>builder()
                .result(bookingService.updateBooking(id, request))
                .build();
    }*/

    //Delete booking
    /*@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteBooking(@PathVariable String id) {
        return ApiResponse.<String>builder()
                .result(bookingService.deleteBooking(id))
                .build();
    }*/
}
