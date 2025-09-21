package com.quynhtien.cineasy.dto.response;

import com.quynhtien.cineasy.entity.Ticket;
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
public class ShowTimeResponse {
    Long id;
    LocalDateTime startTime;
    Long movieId;
    String movieTitle;
    Long auditoriumId;
    String auditoriumName;
    Set<Ticket> tickets;
}
