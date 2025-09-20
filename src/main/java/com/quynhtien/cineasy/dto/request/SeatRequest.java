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
public class SeatRequest {
    @NotBlank(message = "Row label must not be null")
    String rowLabel;

    @Positive(message = "Seat number must not be null")
    int seatNumber;

    @NotNull(message = "Type must not be null")
    String seatType;
}
