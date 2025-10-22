package com.quynhtien.cineasy.dto.request;

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
public class ShowtimeRequest {
    @NotNull(message = "Time must not be null")
    LocalDateTime startTime;

    @Positive(message = "Movie ID must be positive")
    Long movieId;

    @Positive(message = "Auditorium ID must be positive")
    Long auditoriumId;
}
