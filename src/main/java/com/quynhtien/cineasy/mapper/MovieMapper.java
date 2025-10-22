package com.quynhtien.cineasy.mapper;

import com.quynhtien.cineasy.dto.request.MovieRequest;
import com.quynhtien.cineasy.dto.response.MovieResponse;
import com.quynhtien.cineasy.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {ShowtimeMapper.class})
public interface MovieMapper {
    @Mapping(target = "showtimes", ignore = true)
    Movie toMovie(MovieRequest request);

    @Mapping(source = "showtimes", target = "showtimeSummaryResponses")
    MovieResponse toMovieResponse(Movie movie);

    @Mapping(target = "showtimes", ignore = true)
    void updateMovie(MovieRequest request, @MappingTarget Movie movie);
}

