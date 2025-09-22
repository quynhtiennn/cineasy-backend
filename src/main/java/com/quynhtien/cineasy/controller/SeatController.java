package com.quynhtien.cineasy.controller;

import com.quynhtien.cineasy.dto.request.SeatRequest;
import com.quynhtien.cineasy.dto.response.ApiResponse;
import com.quynhtien.cineasy.dto.response.SeatResponse;
import com.quynhtien.cineasy.service.SeatService;
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
@RequestMapping("/seats")
public class SeatController {
    SeatService seatService;

    //Get all seats
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ApiResponse<List<SeatResponse>> findAll() {
        return ApiResponse.<List<SeatResponse>>builder()
                .result(seatService.getSeats())
                .build();
    }

    //Get seat by id
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ApiResponse<SeatResponse> findAll(@PathVariable Long id) {
        return ApiResponse.<SeatResponse>builder()
                .result(seatService.getSeat(id))
                .build();
    }

    //Create seat
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ApiResponse<SeatResponse> createSeat(@RequestBody @Valid SeatRequest request) {
        return ApiResponse.<SeatResponse>builder()
                .result(seatService.createSeat(request))
                .build();
    }

    //Update seat
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ApiResponse<SeatResponse> updateSeat(@PathVariable Long id, @RequestBody @Valid SeatRequest request) {
        return ApiResponse.<SeatResponse>builder()
                .result(seatService.updateSeat(id, request))
                .build();
    }

    //Delete seat
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteSeat(@PathVariable Long id) {
        return ApiResponse.<String>builder()
                .result(seatService.deleteSeat(id))
                .build();
    }
}
