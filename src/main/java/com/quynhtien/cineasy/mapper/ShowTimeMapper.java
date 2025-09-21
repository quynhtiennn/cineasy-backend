package com.quynhtien.cineasy.mapper;

import com.quynhtien.cineasy.dto.request.ShowTimeRequest;
import com.quynhtien.cineasy.dto.response.ShowTimeResponse;
import com.quynhtien.cineasy.entity.ShowTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ShowTimeMapper {
    @Mapping(target = "movie", ignore = true)
    ShowTime toShowTime(ShowTimeRequest request);

    default ShowTimeResponse toShowTimeResponse(ShowTime showTime){
        return ShowTimeResponse.builder()
                .id(showTime.getId())
                .startTime(showTime.getStartTime())
                .movieId(showTime.getMovie().getId())
                .movieTitle(showTime.getMovie().getTitle())
                .build();
    }

    @Mapping(target = "movie", ignore = true)
    void updateShowTime(ShowTimeRequest request, @MappingTarget ShowTime showTime);
}

