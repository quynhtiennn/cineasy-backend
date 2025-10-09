package com.quynhtien.cineasy.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.quynhtien.cineasy.entity.base.BaseLongIdEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class ShowTime extends BaseLongIdEntity {

    LocalDateTime startTime;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    @JsonBackReference
    Movie movie;

    @ManyToOne
    @JoinColumn(name = "auditorium_id")
    @JsonBackReference
    Auditorium auditorium;

    @OneToMany(mappedBy = "showTime", cascade = CascadeType.ALL)
    @JsonManagedReference
    List<Ticket> tickets;
}
