package com.quynhtien.cineasy.controller;

import com.quynhtien.cineasy.dto.request.ShowtimeRequest;
import com.quynhtien.cineasy.dto.response.ApiResponse;
import com.quynhtien.cineasy.dto.response.ShowtimeResponse;
import com.quynhtien.cineasy.service.ShowTimeService;
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
@RequestMapping("/showtimes")
@CrossOrigin(origins = "http://localhost:3000")
public class ShowTimeController {
    ShowTimeService showTimeService;

    //Get all showtimes
    @GetMapping
    public ApiResponse<List<ShowtimeResponse>> findAll() {
        return ApiResponse.<List<ShowtimeResponse>>builder()
                .result(showTimeService.getShowTimes())
                .build();
    }

    //Get show time by id
    @GetMapping("/{id}")
    public ApiResponse<ShowtimeResponse> findAll(@PathVariable Long id) {
        return ApiResponse.<ShowtimeResponse>builder()
                .result(showTimeService.getShowTime(id))
                .build();
    }

    //Create showTime
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ApiResponse<ShowtimeResponse> createShowTime(@RequestBody @Valid ShowtimeRequest request) {
        return ApiResponse.<ShowtimeResponse>builder()
                .result(showTimeService.createShowTime(request))
                .build();
    }

    //Update showTime
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ApiResponse<ShowtimeResponse> updateShowTime(@PathVariable Long id, @RequestBody @Valid ShowtimeRequest request) {
        return ApiResponse.<ShowtimeResponse>builder()
                .result(showTimeService.updateShowTime(id, request))
                .build();
    }

    //Delete showTime
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteShowTime(@PathVariable Long id) {
        return ApiResponse.<String>builder()
                .result(showTimeService.deleteShowTime(id))
                .build();
    }
}
