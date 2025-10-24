package com.quynhtien.cineasy.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.quynhtien.cineasy.entity.Ticket;
import com.quynhtien.cineasy.enums.BookingStatusEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

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

    String movieTitle;
    LocalDateTime showtimeStartTime;

    @JsonProperty("bookingStatus")
    BookingStatusEnum bookingStatusEnum;

    @JsonProperty("tickets")
    List<TicketResponse> ticketResponses;
}
