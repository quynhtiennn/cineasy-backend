package com.quynhtien.cineasy.dto.response;

import com.quynhtien.cineasy.entity.Auditorium;
import com.quynhtien.cineasy.enums.SeatType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SeatResponse {
    Long id;
    String rowLabel;
    int seatNumber;
    SeatType seatType;
    Long auditoriumId;
    String auditoriumName;
}
