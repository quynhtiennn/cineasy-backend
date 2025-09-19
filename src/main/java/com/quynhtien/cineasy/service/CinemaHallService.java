package com.quynhtien.cineasy.service;

import com.quynhtien.cineasy.dto.request.CinemaHallRequest;
import com.quynhtien.cineasy.dto.response.CinemaHallResponse;
import com.quynhtien.cineasy.entity.CinemaHall;
import com.quynhtien.cineasy.exception.AppException;
import com.quynhtien.cineasy.exception.ErrorCode;
import com.quynhtien.cineasy.mapper.CinemaHallMapper;
import com.quynhtien.cineasy.repository.CinemaHallRepository;
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
public class CinemaHallService {
    CinemaHallRepository cinemaHallRepository;
    CinemaHallMapper cinemaHallMapper;

    //Get all cinemaHalls
    public List<CinemaHallResponse> getCinemaHalls() {
        return cinemaHallRepository.findAll().stream()
                .map(cinemaHallMapper::toCinemaHallResponse).toList();
    }

    //Get cinemaHall by id
    public CinemaHallResponse getCinemaHall(Long id) {
        CinemaHall cinemaHall = cinemaHallRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CINEMA_HALL_NOT_FOUND));
        return cinemaHallMapper.toCinemaHallResponse(cinemaHall);
    }

    //Create cinemaHall
    public CinemaHallResponse createCinemaHall(CinemaHallRequest request) {
        CinemaHall cinemaHall = cinemaHallMapper.toCinemaHall(request);
        cinemaHallRepository.save(cinemaHall);
        return cinemaHallMapper.toCinemaHallResponse(cinemaHall);
    }

    //Update cinemaHall
    public CinemaHallResponse updateCinemaHall(Long id, CinemaHallRequest request) {
        CinemaHall cinemaHall = cinemaHallRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CINEMA_HALL_NOT_FOUND));

        cinemaHallMapper.updateCinemaHall(request, cinemaHall);
        cinemaHallRepository.save(cinemaHall);
        return cinemaHallMapper.toCinemaHallResponse(cinemaHall);
    }

    //Delete cinemaHall
    public String deleteCinemaHall(Long id) {
        if (!cinemaHallRepository.existsById(id)) {
            throw new AppException(ErrorCode.CINEMA_HALL_NOT_FOUND);
        }

        cinemaHallRepository.deleteById(id);
        return "Delete cinema hall successfully";
    }
}
