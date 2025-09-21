package com.quynhtien.cineasy.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    double price;
    boolean available;

    /*@ManyToOne
    Booking booking;*/

    @ManyToOne
    @JoinColumn(name = "show_time_id")
    @JsonBackReference
    ShowTime showTime;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    @JsonManagedReference
    Seat seat;

}
