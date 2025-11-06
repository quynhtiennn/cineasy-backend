package com.quynhtien.cineasy.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class MovieResponse {
    Long id;
    String title;
    String description;
    String genre;
    int duration;
    String rating;
    LocalDate releaseDate;
    String posterUrl;
    String backdropUrl;
    String director;

    String actors;

    @JsonProperty("showtimeSummaries")
    List<ShowtimeSummaryResponse> showtimeSummaryResponses;
}
