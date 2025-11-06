package com.quynhtien.cineasy.service;

import com.quynhtien.cineasy.dto.request.MovieRequest;
import com.quynhtien.cineasy.dto.response.FileInfoResponse;
import com.quynhtien.cineasy.dto.response.MovieResponse;
import com.quynhtien.cineasy.entity.Movie;
import com.quynhtien.cineasy.enums.MovieStatusEnum;
import com.quynhtien.cineasy.exception.AppException;
import com.quynhtien.cineasy.exception.ErrorCode;
import com.quynhtien.cineasy.mapper.MovieMapper;
import com.quynhtien.cineasy.repository.MovieRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class MovieService {
    MovieRepository movieRepository;
    MovieMapper movieMapper;
    FileService fileService;

    //Get all movies
    public List<MovieResponse> getMovies() {
        List<Movie> movies = movieRepository
                .findByStatus(MovieStatusEnum.ACTIVE);
        return movies.stream()
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

    public MovieResponse updatePoster(MultipartFile file, Long id) throws IOException {

        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.MOVIE_NOT_FOUND));

        FileInfoResponse fileInfoResponse = fileService.uploadFile(file);

        movie.setPosterUrl(fileInfoResponse.getUrl());

        return movieMapper.toMovieResponse(movieRepository.save(movie));
    }

    public MovieResponse updateBackdrop(MultipartFile file, Long id) throws IOException {

        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.MOVIE_NOT_FOUND));

        FileInfoResponse fileInfoResponse = fileService.uploadFile(file);

        movie.setBackdropUrl(fileInfoResponse.getUrl());

        return movieMapper.toMovieResponse(movieRepository.save(movie));
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
