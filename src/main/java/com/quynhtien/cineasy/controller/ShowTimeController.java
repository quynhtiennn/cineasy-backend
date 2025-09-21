package com.quynhtien.cineasy.controller;

import com.quynhtien.cineasy.dto.request.ShowTimeRequest;
import com.quynhtien.cineasy.dto.response.ApiResponse;
import com.quynhtien.cineasy.dto.response.ShowTimeResponse;
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
@RequestMapping("/showTimes")
public class ShowTimeController {
    ShowTimeService showTimeService;

    //Get all showTimes
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ApiResponse<List<ShowTimeResponse>> findAll() {
        return ApiResponse.<List<ShowTimeResponse>>builder()
                .result(showTimeService.getShowTimes())
                .build();
    }

    //Create showTime
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ApiResponse<ShowTimeResponse> createShowTime(@RequestBody @Valid ShowTimeRequest request) {
        return ApiResponse.<ShowTimeResponse>builder()
                .result(showTimeService.createShowTime(request))
                .build();
    }

    //Update showTime
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ApiResponse<ShowTimeResponse> updateShowTime(@PathVariable Long id, @RequestBody @Valid ShowTimeRequest request) {
        return ApiResponse.<ShowTimeResponse>builder()
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
