package com.quynhtien.cineasy.dto.response;

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
public class ShowtimeSummaryResponse {
    Long id;
    LocalDateTime startTime;
    int availableCount;
    double minPrice;
    double maxPrice;
}
