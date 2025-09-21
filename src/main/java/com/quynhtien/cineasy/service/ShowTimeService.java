package com.quynhtien.cineasy.service;

import com.quynhtien.cineasy.dto.request.ShowTimeRequest;
import com.quynhtien.cineasy.dto.response.ShowTimeResponse;
import com.quynhtien.cineasy.entity.Auditorium;
import com.quynhtien.cineasy.entity.Movie;
import com.quynhtien.cineasy.entity.ShowTime;
import com.quynhtien.cineasy.exception.AppException;
import com.quynhtien.cineasy.exception.ErrorCode;
import com.quynhtien.cineasy.mapper.ShowTimeMapper;
import com.quynhtien.cineasy.repository.AuditoriumRepository;
import com.quynhtien.cineasy.repository.MovieRepository;
import com.quynhtien.cineasy.repository.ShowTimeRepository;
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
public class ShowTimeService {
    ShowTimeRepository showTimeRepository;
    ShowTimeMapper showTimeMapper;
    MovieRepository movieRepository;
    AuditoriumRepository auditoriumRepository;

    //Get all showTimes
    public List<ShowTimeResponse> getShowTimes() {
        return showTimeRepository.findAll().stream()
                .map(showTimeMapper::toShowTimeResponse).toList();
    }

    //Get showTime by id
    public ShowTimeResponse getShowTime(Long id) {
        ShowTime showTime = showTimeRepository.findById(id)
                    .orElseThrow(() -> new AppException(ErrorCode.SHOWTIME_NOT_FOUND));
        return showTimeMapper.toShowTimeResponse(showTime);
    }

    //Create showTime
    public ShowTimeResponse createShowTime(ShowTimeRequest request) {
        ShowTime showTime = showTimeMapper.toShowTime(request);
        Movie movie = movieRepository.findById(request.getMovieId())
                        .orElseThrow(()-> new AppException(ErrorCode.SHOWTIME_NOT_FOUND));
        showTime.setMovie(movie);

        Auditorium auditorium = auditoriumRepository.findById(request.getAuditoriumId())
                        .orElseThrow(()-> new AppException(ErrorCode.AUDITORIUM_NOT_FOUND));
        showTime.setAuditorium(auditorium);

        showTimeRepository.save(showTime);
        return showTimeMapper.toShowTimeResponse(showTime);
    }

    //Update showTime
    public ShowTimeResponse updateShowTime(Long id, ShowTimeRequest request) {
        ShowTime showTime = showTimeRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SEAT_NOT_FOUND));

        showTimeMapper.updateShowTime(request, showTime);

        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(()-> new AppException(ErrorCode.SHOWTIME_NOT_FOUND));
        showTime.setMovie(movie);

        Auditorium auditorium = auditoriumRepository.findById(request.getAuditoriumId())
                .orElseThrow(()-> new AppException(ErrorCode.AUDITORIUM_NOT_FOUND));
        showTime.setAuditorium(auditorium);

        showTimeRepository.save(showTime);
        return showTimeMapper.toShowTimeResponse(showTime);
    }

    //Delete showTime
    public String deleteShowTime(Long id) {
        if (!showTimeRepository.existsById(id)) {
            throw new AppException(ErrorCode.SEAT_NOT_FOUND);
        }

        showTimeRepository.deleteById(id);
        return "Delete show time successfully";
    }
}
