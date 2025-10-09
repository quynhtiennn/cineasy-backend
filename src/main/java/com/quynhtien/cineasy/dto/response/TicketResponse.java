package com.quynhtien.cineasy.dto.response;

import com.quynhtien.cineasy.entity.Seat;
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
    Seat seat;
}
