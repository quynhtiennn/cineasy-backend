package com.quynhtien.cineasy.mapper;

import com.quynhtien.cineasy.dto.request.AuditoriumRequest;
import com.quynhtien.cineasy.dto.response.AuditoriumResponse;
import com.quynhtien.cineasy.entity.Auditorium;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {SeatMapper.class})
public interface AuditoriumMapper {
    @Mapping(target = "showtimes", ignore = true)
    @Mapping(target = "seats", ignore = true)
    Auditorium toAuditorium(AuditoriumRequest request);

    @Mapping(source = "seats", target = "seatResponses")
    AuditoriumResponse toAuditoriumResponse(Auditorium auditorium);

    @Mapping(target = "showtimes", ignore = true)
    @Mapping(target = "seats", ignore = true)
    void updateAuditorium(AuditoriumRequest request, @MappingTarget Auditorium auditorium);
}

