package com.quynhtien.cineasy.dto.response;

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

    String rowLabel;
    int seatNumber;
}
