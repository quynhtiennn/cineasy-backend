package com.quynhtien.cineasy.service;

import com.quynhtien.cineasy.dto.request.AuditoriumRequest;
import com.quynhtien.cineasy.dto.response.AuditoriumResponse;
import com.quynhtien.cineasy.entity.Auditorium;
import com.quynhtien.cineasy.entity.Seat;
import com.quynhtien.cineasy.enums.SeatType;
import com.quynhtien.cineasy.exception.AppException;
import com.quynhtien.cineasy.exception.ErrorCode;
import com.quynhtien.cineasy.mapper.AuditoriumMapper;
import com.quynhtien.cineasy.repository.AuditoriumRepository;
import com.quynhtien.cineasy.repository.SeatRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class AuditoriumService {
    AuditoriumRepository auditoriumRepository;
    AuditoriumMapper auditoriumMapper;
    SeatRepository seatRepository;

    //Get all auditoriums
    public List<AuditoriumResponse> getAuditoriums() {
        return auditoriumRepository.findAll().stream()
                .map(auditoriumMapper::toAuditoriumResponse).toList();
    }

    //Get auditorium by id
    public AuditoriumResponse getAuditorium(Long id) {
        Auditorium auditorium = auditoriumRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.AUDITORIUM_NOT_FOUND));
        return auditoriumMapper.toAuditoriumResponse(auditorium);
    }

    //Create auditorium
    public AuditoriumResponse createAuditorium(AuditoriumRequest request) {
        Auditorium auditorium = auditoriumMapper.toAuditorium(request);
        List<Seat> seats = new ArrayList<>();

        for (int row = 0; row < auditorium.getTotalRows(); row++) {
            char rowLabel = (char) ('A' + row);
            for (int seatNum = 1; seatNum <= auditorium.getSeatsPerRow(); seatNum++) {
                Seat seat = Seat.builder()
                        .rowLabel(String.valueOf(rowLabel))
                        .seatNumber(seatNum)
                        .seatType(SeatType.REGULAR)
                        .auditorium(auditorium)
                        .build();
                seats.add(seat);
            }
        }

        auditorium.setSeats(seats);
        auditoriumRepository.save(auditorium);
        return auditoriumMapper.toAuditoriumResponse(auditorium);
    }

    //Update auditorium
    public AuditoriumResponse updateAuditorium(Long id, AuditoriumRequest request) {
        Auditorium auditorium = auditoriumRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.AUDITORIUM_NOT_FOUND));

        auditoriumMapper.updateAuditorium(request, auditorium);
        auditoriumRepository.save(auditorium);
        return auditoriumMapper.toAuditoriumResponse(auditorium);
    }

    //Delete auditorium
    public String deleteAuditorium(Long id) {
        if (!auditoriumRepository.existsById(id)) {
            throw new AppException(ErrorCode.AUDITORIUM_NOT_FOUND);
        }

        auditoriumRepository.deleteById(id);
        return "Delete cinema hall successfully";
    }
}
