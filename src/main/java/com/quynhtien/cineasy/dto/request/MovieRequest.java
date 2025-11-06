package com.quynhtien.cineasy.dto.request;

import com.quynhtien.cineasy.enums.MovieStatusEnum;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieRequest {
    @NotBlank(message = "Title is required")
    String title;

    @NotBlank(message = "Description is required")
    String description;

    @NotBlank(message = "Genre is required")
    String genre;

    @Min(value = 1, message = "Duration must be at least 1 minute")
    @Max(value = 200, message = "Duration cannot exceed 200 minutes")
    int duration; // minutes

    @NotBlank(message = "Rating is required")
    String rating;

    @NotNull(message = "Release date is required")
    LocalDate releaseDate;

    @NotBlank(message = "Director is required")
    String director;

    @NotBlank(message = "Actors are required")
    String actors;

    @NotBlank(message = "Status are required")
    MovieStatusEnum status;
}
