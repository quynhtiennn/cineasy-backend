package com.quynhtien.cineasy.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.quynhtien.cineasy.entity.base.BaseLongIdEntity;
import com.quynhtien.cineasy.enums.MovieStatusEnum;
import com.quynhtien.cineasy.enums.PaymentMethodEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
public class Movie extends BaseLongIdEntity {

    @Column(unique = true)
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

    @Enumerated(EnumType.STRING)
    MovieStatusEnum status;


    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    @JsonManagedReference
    List<Showtime> showtimes;




}
