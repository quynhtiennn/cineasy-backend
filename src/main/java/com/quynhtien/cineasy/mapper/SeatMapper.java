package com.quynhtien.cineasy.mapper;

import com.quynhtien.cineasy.dto.request.SeatRequest;
import com.quynhtien.cineasy.dto.response.SeatResponse;
import com.quynhtien.cineasy.entity.Seat;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SeatMapper {
    Seat toSeat(SeatRequest request);

    SeatResponse toSeatResponse(Seat seat);

    void updateSeat(SeatRequest request, @MappingTarget Seat seat);
}

