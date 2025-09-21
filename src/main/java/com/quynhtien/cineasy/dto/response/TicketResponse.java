package com.quynhtien.cineasy.dto.response;

import com.quynhtien.cineasy.entity.Seat;
import com.quynhtien.cineasy.entity.ShowTime;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

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
