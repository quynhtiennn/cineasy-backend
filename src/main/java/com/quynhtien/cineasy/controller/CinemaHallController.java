package com.quynhtien.cineasy.controller;

import com.quynhtien.cineasy.dto.request.CinemaHallRequest;
import com.quynhtien.cineasy.dto.response.ApiResponse;
import com.quynhtien.cineasy.dto.response.CinemaHallResponse;
import com.quynhtien.cineasy.service.CinemaHallService;
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
@RequestMapping("/cinemaHalls")
public class CinemaHallController {
    CinemaHallService cinemaHallService;

    //Get all cinemaHalls
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ApiResponse<List<CinemaHallResponse>> findAll() {
        return ApiResponse.<List<CinemaHallResponse>>builder()
                .result(cinemaHallService.getCinemaHalls())
                .build();
    }

    //Create cinemaHall
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ApiResponse<CinemaHallResponse> createCinemaHall(@RequestBody @Valid CinemaHallRequest request) {
        return ApiResponse.<CinemaHallResponse>builder()
                .result(cinemaHallService.createCinemaHall(request))
                .build();
    }

    //Update cinemaHall
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ApiResponse<CinemaHallResponse> updateCinemaHall(@PathVariable Long id, @RequestBody @Valid CinemaHallRequest request) {
        return ApiResponse.<CinemaHallResponse>builder()
                .result(cinemaHallService.updateCinemaHall(id, request))
                .build();
    }

    //Delete cinemaHall
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteCinemaHall(@PathVariable Long id) {
        return ApiResponse.<String>builder()
                .result(cinemaHallService.deleteCinemaHall(id))
                .build();
    }
}
