package com.quynhtien.cineasy.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.quynhtien.cineasy.entity.base.BaseLongIdEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
public class Auditorium extends BaseLongIdEntity {

    @Column(unique = true, nullable = false)
    String name;

    int totalRows;
    int seatsPerRow;

    @OneToMany(mappedBy = "auditorium", cascade = CascadeType.ALL)
    @JsonManagedReference
    List<Seat> seats;

    @OneToMany(mappedBy = "auditorium", cascade = CascadeType.ALL)
    @JsonManagedReference
    List<Showtime> showtimes;


}
