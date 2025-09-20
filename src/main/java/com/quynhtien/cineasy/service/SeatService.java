package com.quynhtien.cineasy.service;

import com.quynhtien.cineasy.dto.request.SeatRequest;
import com.quynhtien.cineasy.dto.response.SeatResponse;
import com.quynhtien.cineasy.entity.Seat;
import com.quynhtien.cineasy.exception.AppException;
import com.quynhtien.cineasy.exception.ErrorCode;
import com.quynhtien.cineasy.mapper.SeatMapper;
import com.quynhtien.cineasy.repository.SeatRepository;
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
public class SeatService {
    SeatRepository seatRepository;
    SeatMapper seatMapper;

    //Get all seats
    public List<SeatResponse> getSeats() {
        return seatRepository.findAll().stream()
                .map(seatMapper::toSeatResponse).toList();
    }

    //Get seat by id
    public SeatResponse getSeat(Long id) {
        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SEAT_NOT_FOUND));
        return seatMapper.toSeatResponse(seat);
    }

    //Create seat
    public SeatResponse createSeat(SeatRequest request) {
        Seat seat = seatMapper.toSeat(request);
        seatRepository.save(seat);
        return seatMapper.toSeatResponse(seat);
    }

    //Update seat
    public SeatResponse updateSeat(Long id, SeatRequest request) {
        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SEAT_NOT_FOUND));

        seatMapper.updateSeat(request, seat);
        seatRepository.save(seat);
        return seatMapper.toSeatResponse(seat);
    }

    //Delete seat
    public String deleteSeat(Long id) {
        if (!seatRepository.existsById(id)) {
            throw new AppException(ErrorCode.SEAT_NOT_FOUND);
        }

        seatRepository.deleteById(id);
        return "Delete seat successfully";
    }
}
