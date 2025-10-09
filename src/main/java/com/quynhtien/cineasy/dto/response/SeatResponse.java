package com.quynhtien.cineasy.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.quynhtien.cineasy.enums.SeatTypeEnum;
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

    @JsonProperty("seatType")
    SeatTypeEnum seatTypeEnum;

    Long auditoriumId;
    String auditoriumName;
}
