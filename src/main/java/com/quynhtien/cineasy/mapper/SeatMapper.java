package com.quynhtien.cineasy.mapper;

import com.quynhtien.cineasy.dto.request.SeatRequest;
import com.quynhtien.cineasy.dto.response.SeatResponse;
import com.quynhtien.cineasy.entity.Seat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SeatMapper {
    @Mapping(target = "auditorium", ignore = true)
    Seat toSeat(SeatRequest request);

    default SeatResponse toSeatResponse(Seat seat){
        return SeatResponse.builder()
                .id(seat.getId())
                .seatType(seat.getSeatType())
                .rowLabel(seat.getRowLabel())
                .seatNumber(seat.getSeatNumber())
                .auditoriumId(seat.getAuditorium().getId())
                .auditoriumName(seat.getAuditorium().getName())
                .build();
    }

    @Mapping(target = "auditorium", ignore = true)
    void updateSeat(SeatRequest request, @MappingTarget Seat seat);
}

