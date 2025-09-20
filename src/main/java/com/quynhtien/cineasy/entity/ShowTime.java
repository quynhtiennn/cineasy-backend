package com.quynhtien.cineasy.entity;

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

    /*@ManyToOne
    Movie movie;

    @ManyToOne
    CinemaHall hall;

    @OneToMany(mappedBy = "showtime", cascade = CascadeType.ALL)
    List<Ticket> tickets;*/
}
