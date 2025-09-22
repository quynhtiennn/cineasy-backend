package com.quynhtien.cineasy.controller;

import com.quynhtien.cineasy.dto.request.AuditoriumRequest;
import com.quynhtien.cineasy.dto.response.ApiResponse;
import com.quynhtien.cineasy.dto.response.AuditoriumResponse;
import com.quynhtien.cineasy.dto.response.BookingResponse;
import com.quynhtien.cineasy.service.AuditoriumService;
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
@RequestMapping("/auditoriums")
public class AuditoriumController {
    AuditoriumService auditoriumService;

    //Get all auditoriums
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ApiResponse<List<AuditoriumResponse>> findAll() {
        return ApiResponse.<List<AuditoriumResponse>>builder()
                .result(auditoriumService.getAuditoriums())
                .build();
    }

    //Get auditorium by id
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ApiResponse<AuditoriumResponse> findAll(@PathVariable Long id) {
        return ApiResponse.<AuditoriumResponse>builder()
                .result(auditoriumService.getAuditorium(id))
                .build();
    }

    //Create auditorium
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ApiResponse<AuditoriumResponse> createAuditorium(@RequestBody @Valid AuditoriumRequest request) {
        return ApiResponse.<AuditoriumResponse>builder()
                .result(auditoriumService.createAuditorium(request))
                .build();
    }

    //Update auditorium
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ApiResponse<AuditoriumResponse> updateAuditorium(@PathVariable Long id, @RequestBody @Valid AuditoriumRequest request) {
        return ApiResponse.<AuditoriumResponse>builder()
                .result(auditoriumService.updateAuditorium(id, request))
                .build();
    }

    //Delete auditorium
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteAuditorium(@PathVariable Long id) {
        return ApiResponse.<String>builder()
                .result(auditoriumService.deleteAuditorium(id))
                .build();
    }
}
