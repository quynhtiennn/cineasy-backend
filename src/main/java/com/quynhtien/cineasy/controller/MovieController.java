package com.quynhtien.cineasy.controller;

import com.quynhtien.cineasy.dto.request.MovieRequest;
import com.quynhtien.cineasy.dto.response.ApiResponse;
import com.quynhtien.cineasy.dto.response.AuditoriumResponse;
import com.quynhtien.cineasy.dto.response.MovieResponse;
import com.quynhtien.cineasy.service.MovieService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.Context;
import java.io.IOException;
import java.util.List;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping("/movies")
public class MovieController {
    MovieService movieService;

    //Get all movies
    @GetMapping
    public ApiResponse<List<MovieResponse>> findAll() {
        return ApiResponse.<List<MovieResponse>>builder()
                .result(movieService.getMovies())
                .build();
    }

    //Get movie by id
    @GetMapping("/{id}")
    public ApiResponse<MovieResponse> findAll(@PathVariable Long id) {
        return ApiResponse.<MovieResponse>builder()
                .result(movieService.getMovie(id))
                .build();
    }

    //Create movie
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ApiResponse<MovieResponse> createMovie(@RequestBody @Valid MovieRequest request) {
        return ApiResponse.<MovieResponse>builder()
                .result(movieService.createMovie(request))
                .build();
    }

    //Update movie
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ApiResponse<MovieResponse> updateMovie(@PathVariable Long id, @RequestBody @Valid MovieRequest request) {
        return ApiResponse.<MovieResponse>builder()
                .result(movieService.updateMovie(id, request))
                .build();
    }

    //Update poster
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/poster")
    public ApiResponse<MovieResponse> updatePoster(@PathVariable Long id,
                                                   @RequestParam("file") MultipartFile file) throws IOException {
        return ApiResponse.<MovieResponse>builder()
                .result(movieService.updatePoster(file, id))
                .build();
    }

    //Update backdrop
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/backdrop")
    public ApiResponse<MovieResponse> updateBackdrop(@PathVariable Long id,
                                                   @RequestParam("file") MultipartFile file) throws IOException {
        return ApiResponse.<MovieResponse>builder()
                .result(movieService.updateBackdrop(file, id))
                .build();
    }

    //Delete movie
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteMovie(@PathVariable Long id) {
        return ApiResponse.<String>builder()
                .result(movieService.deleteMovie(id))
                .build();
    }
}
