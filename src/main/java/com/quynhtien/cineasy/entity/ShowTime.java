package com.quynhtien.cineasy.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class ShowTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    LocalDateTime startTime;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    @JsonBackReference
    Movie movie;

   /* @ManyToOne
    Auditorium hall;*/

    /*@OneToMany(mappedBy = "showtime", cascade = CascadeType.ALL)
    List<Ticket> tickets;*/
}
