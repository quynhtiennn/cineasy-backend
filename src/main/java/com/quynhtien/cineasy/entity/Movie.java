package com.quynhtien.cineasy.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    String title;

    String description;
    String genre;
    int duration; // minutes
    LocalDate releaseDate;

//    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
//    private List<Showtime> showtimes;


}
