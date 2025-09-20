package com.quynhtien.cineasy.mapper;

import com.quynhtien.cineasy.dto.request.AuditoriumRequest;
import com.quynhtien.cineasy.dto.response.AuditoriumResponse;
import com.quynhtien.cineasy.entity.Auditorium;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AuditoriumMapper {
    Auditorium toAuditorium(AuditoriumRequest request);

    AuditoriumResponse toAuditoriumResponse(Auditorium auditorium);

    void updateAuditorium(AuditoriumRequest request, @MappingTarget Auditorium auditorium);
}

