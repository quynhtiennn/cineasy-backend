package com.quynhtien.cineasy.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShowTimeRequest {
    @NotNull(message = "Time must not be null")
    LocalDateTime startTime;

    @Positive(message = "Movie ID must be positive")
    Long movieId;
}
