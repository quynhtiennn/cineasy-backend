package com.quynhtien.cineasy.mapper;

import com.quynhtien.cineasy.dto.request.CinemaHallRequest;
import com.quynhtien.cineasy.dto.response.CinemaHallResponse;
import com.quynhtien.cineasy.entity.CinemaHall;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CinemaHallMapper {
    CinemaHall toCinemaHall(CinemaHallRequest request);

    CinemaHallResponse toCinemaHallResponse(CinemaHall cinemaHall);

    void updateCinemaHall(CinemaHallRequest request, @MappingTarget CinemaHall cinemaHall);
}

