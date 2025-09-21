package com.quynhtien.cineasy.service;

import com.quynhtien.cineasy.dto.request.MovieRequest;
import com.quynhtien.cineasy.dto.response.MovieResponse;
import com.quynhtien.cineasy.entity.Movie;
import com.quynhtien.cineasy.exception.AppException;
import com.quynhtien.cineasy.exception.ErrorCode;
import com.quynhtien.cineasy.mapper.MovieMapper;
import com.quynhtien.cineasy.repository.MovieRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class MovieService {
    MovieRepository movieRepository;
    MovieMapper movieMapper;

    //Get all movies
    public List<MovieResponse> getMovies() {
        return movieRepository.findAll().stream()
                .map(movieMapper::toMovieResponse).toList();
    }

    //Get movie by id
    public MovieResponse getMovie(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.MOVIE_NOT_FOUND));
        return movieMapper.toMovieResponse(movie);
    }

    //Create movie
    public MovieResponse createMovie(MovieRequest request) {
        Movie movie = movieMapper.toMovie(request);
        movieRepository.save(movie);
        return movieMapper.toMovieResponse(movie);
    }

    //Update movie
    public MovieResponse updateMovie(Long id, MovieRequest request) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.MOVIE_NOT_FOUND));

        movieMapper.updateMovie(request, movie);
        movieRepository.save(movie);
        return movieMapper.toMovieResponse(movie);
    }

    //Delete movie
    public String deleteMovie(Long id) {
        if (!movieRepository.existsById(id)) {
            throw new AppException(ErrorCode.MOVIE_NOT_FOUND);
        }

        movieRepository.deleteById(id);
        return "Delete movie successfully";
    }
}
