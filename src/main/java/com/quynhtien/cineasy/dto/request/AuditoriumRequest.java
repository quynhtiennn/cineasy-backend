package com.quynhtien.cineasy.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuditoriumRequest {
    @NotBlank(message = "Name is required")
    String name;

    @Positive(message = "Rows must be a positive number")
    int totalRows;

    @Positive(message = "Seats per row must be a positive number")
    int seatsPerRow;
}
