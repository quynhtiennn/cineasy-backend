package com.quynhtien.cineasy.mapper;

import com.quynhtien.cineasy.dto.request.AuditoriumRequest;
import com.quynhtien.cineasy.dto.response.AuditoriumResponse;
import com.quynhtien.cineasy.entity.Auditorium;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AuditoriumMapper {
    @Mapping(target = "showTimes", ignore = true)
    @Mapping(target = "seats", ignore = true)
    Auditorium toAuditorium(AuditoriumRequest request);

    AuditoriumResponse toAuditoriumResponse(Auditorium auditorium);

    @Mapping(target = "showTimes", ignore = true)
    @Mapping(target = "seats", ignore = true)
    void updateAuditorium(AuditoriumRequest request, @MappingTarget Auditorium auditorium);
}

