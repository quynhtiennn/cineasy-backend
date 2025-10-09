package com.quynhtien.cineasy.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.quynhtien.cineasy.entity.ShowTime;
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

    @JsonProperty("seats")
    List<SeatResponse> seatResponses;

    List<ShowTime> showTimes;
}
