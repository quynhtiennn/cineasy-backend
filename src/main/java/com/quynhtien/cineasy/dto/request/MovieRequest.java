package com.quynhtien.cineasy.dto.request;

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
    String description;
    String genre;
    int duration; // minutes

    @NotNull(message = "Release date is required")
    LocalDate releaseDate;
}
