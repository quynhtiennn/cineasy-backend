package com.quynhtien.cineasy.mapper;

import com.quynhtien.cineasy.dto.request.MovieRequest;
import com.quynhtien.cineasy.dto.response.MovieResponse;
import com.quynhtien.cineasy.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    Movie toMovie(MovieRequest request);

    MovieResponse toMovieResponse(Movie movie);

    void updateMovie(MovieRequest request, @MappingTarget Movie movie);
}

