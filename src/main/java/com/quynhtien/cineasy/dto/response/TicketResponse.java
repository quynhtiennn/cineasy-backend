package com.quynhtien.cineasy.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketResponse {
    Long id;
    double price;
    boolean available;

//    @JsonProperty("seat")
////    SeatResponse seatResponse;
    String rowLabel;
    int seatNumber;
}
