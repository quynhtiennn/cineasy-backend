package com.quynhtien.cineasy.dto.response;

import com.quynhtien.cineasy.entity.Seat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuditoriumResponse {
    Long id;
    String name;
    int totalRows;
    int seatsPerRow;
    List<Seat> seats;
}
