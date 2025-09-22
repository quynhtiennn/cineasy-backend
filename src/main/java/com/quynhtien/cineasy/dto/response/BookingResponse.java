package com.quynhtien.cineasy.dto.response;

import com.quynhtien.cineasy.entity.Ticket;
import com.quynhtien.cineasy.enums.BookingStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingResponse {
    String id;
    LocalDateTime bookingTime;
    double totalPrice;
    BookingStatus bookingStatus;
    Set<Ticket> tickets;
}
