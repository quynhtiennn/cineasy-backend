package com.quynhtien.cineasy.service;

import com.quynhtien.cineasy.dto.request.ShowtimeRequest;
import com.quynhtien.cineasy.dto.response.ShowtimeResponse;
import com.quynhtien.cineasy.entity.Auditorium;
import com.quynhtien.cineasy.entity.Movie;
import com.quynhtien.cineasy.entity.Showtime;
import com.quynhtien.cineasy.entity.Ticket;
import com.quynhtien.cineasy.exception.AppException;
import com.quynhtien.cineasy.exception.ErrorCode;
import com.quynhtien.cineasy.mapper.ShowtimeMapper;
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
    ShowTimeRepository showtimeRepository;
    ShowtimeMapper showtimeMapper;
    MovieRepository movieRepository;
    AuditoriumRepository auditoriumRepository;

    //Get all showtimes
    public List<ShowtimeResponse> getShowTimes() {
        return showtimeRepository.findAll().stream()
                .map(showtimeMapper::toShowtimeResponse).toList();
    }

    //Get showTime by id
    public ShowtimeResponse getShowTime(Long id) {
        Showtime showTime = showtimeRepository.findById(id)
                    .orElseThrow(() -> new AppException(ErrorCode.SHOWTIME_NOT_FOUND));
        return showtimeMapper.toShowtimeResponse(showTime);
    }

    //Create showTime
    public ShowtimeResponse createShowTime(ShowtimeRequest request) {
        Showtime showtime = showtimeMapper.toShowtime(request);
        Movie movie = movieRepository.findById(request.getMovieId())
                        .orElseThrow(()-> new AppException(ErrorCode.MOVIE_NOT_FOUND));
        showtime.setMovie(movie);

        Auditorium auditorium = auditoriumRepository.findById(request.getAuditoriumId())
                        .orElseThrow(()-> new AppException(ErrorCode.AUDITORIUM_NOT_FOUND));
        showtime.setAuditorium(auditorium);

        List<Ticket> tickets = auditorium.getSeats().stream()
                .map(seat -> Ticket.builder()
                        .price(seat.getSeatTypeEnum().getPrice())
                        .available(true)
                        .seat(seat)
                        .showtime(showtime) // link back
                        .build()).toList();
        showtime.setTickets(tickets);

        showtimeRepository.save(showtime);
        return showtimeMapper.toShowtimeResponse(showtime);
    }

    //Update showTime
    public ShowtimeResponse updateShowTime(Long id, ShowtimeRequest request) {
        Showtime showTime = showtimeRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SEAT_NOT_FOUND));

        showtimeMapper.updateShowtime(request, showTime);

        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(()-> new AppException(ErrorCode.SHOWTIME_NOT_FOUND));
        showTime.setMovie(movie);

        Auditorium auditorium = auditoriumRepository.findById(request.getAuditoriumId())
                .orElseThrow(()-> new AppException(ErrorCode.AUDITORIUM_NOT_FOUND));
        showTime.setAuditorium(auditorium);

        showtimeRepository.save(showTime);
        return showtimeMapper.toShowtimeResponse(showTime);
    }

    //Delete showTime
    public String deleteShowTime(Long id) {
        if (!showtimeRepository.existsById(id)) {
            throw new AppException(ErrorCode.SEAT_NOT_FOUND);
        }

        showtimeRepository.deleteById(id);
        return "Delete showtime successfully";
    }
}
