package com.quynhtien.cineasy.dto.response;

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
public class MovieResponse {
    Long id;
    String title;
    String description;
    String genre;
    int duration;
    LocalDate releaseDate;
    List<ShowTime> showTimes;
}
