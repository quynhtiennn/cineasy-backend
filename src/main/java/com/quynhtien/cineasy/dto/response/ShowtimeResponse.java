package com.quynhtien.cineasy.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.quynhtien.cineasy.entity.Ticket;
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
public class ShowtimeResponse {
    Long id;
    LocalDateTime startTime;
    Long movieId;
    String movieTitle;
    Long auditoriumId;
    String auditoriumName;
    int totalRows;
    int seatsPerRow;

    @JsonProperty("tickets")
    List<TicketResponse> ticketResponses;
}
