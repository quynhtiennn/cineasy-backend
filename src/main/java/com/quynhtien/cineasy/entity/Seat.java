package com.quynhtien.cineasy.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.quynhtien.cineasy.entity.base.BaseLongIdEntity;
import com.quynhtien.cineasy.enums.SeatTypeEnum;
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
public class Seat extends BaseLongIdEntity {

    String rowLabel;
    int seatNumber;

    SeatTypeEnum seatTypeEnum;

    @ManyToOne
    @JoinColumn(name = "auditorium_id")
    @JsonBackReference
    Auditorium auditorium;

    @OneToMany(mappedBy = "seat", cascade = CascadeType.ALL)
    @JsonBackReference
    List<Ticket> tickets;
}
