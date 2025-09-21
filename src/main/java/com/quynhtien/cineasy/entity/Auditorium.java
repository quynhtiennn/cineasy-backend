package com.quynhtien.cineasy.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
public class Auditorium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true, nullable = false)
    String name;

    int totalRows;
    int seatsPerRow;

    @OneToMany(mappedBy = "auditorium", cascade = CascadeType.ALL)
    @JsonManagedReference
    Set<Seat> seats;

    @OneToMany(mappedBy = "auditorium", cascade = CascadeType.ALL)
    @JsonManagedReference
    Set<ShowTime> showTimes;


}
