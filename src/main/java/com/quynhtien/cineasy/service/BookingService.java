package com.quynhtien.cineasy.service;

import com.quynhtien.cineasy.dto.request.BookingRequest;
import com.quynhtien.cineasy.dto.response.BookingResponse;
import com.quynhtien.cineasy.entity.Booking;
import com.quynhtien.cineasy.entity.Ticket;
import com.quynhtien.cineasy.entity.User;
import com.quynhtien.cineasy.enums.BookingStatusEnum;
import com.quynhtien.cineasy.exception.AppException;
import com.quynhtien.cineasy.exception.ErrorCode;
import com.quynhtien.cineasy.mapper.BookingMapper;
import com.quynhtien.cineasy.repository.BookingRepository;
import com.quynhtien.cineasy.repository.PaymentRepository;
import com.quynhtien.cineasy.repository.TicketRepository;
import com.quynhtien.cineasy.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class BookingService {
    BookingRepository bookingRepository;
    BookingMapper bookingMapper;
    TicketRepository ticketRepository;
    PaymentRepository paymentRepository;
    UserRepository userRepository;

    //Get all bookings
    public List<BookingResponse> getBookings() {
        return bookingRepository.findAll().stream()
                .map(bookingMapper::toBookingResponse).toList();
    }

    //Get booking by id
    public BookingResponse getBooking(String id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOT_FOUND));
        return bookingMapper.toBookingResponse(booking);
    }

    //Create booking
    public BookingResponse createBooking(BookingRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        List<Ticket> tickets = ticketRepository.findAllById(request.getTickets());
        if (tickets.size() != request.getTickets().size()) {
            throw new AppException(ErrorCode.TICKET_NOT_FOUND);
        }

        Booking booking = Booking.builder()
                .bookingTime(LocalDateTime.now())
                .bookingStatusEnum(BookingStatusEnum.PENDING)
                .totalPrice(tickets.stream().mapToDouble(Ticket::getPrice).sum())
                .tickets(tickets)
                .user(user)
                .build();

        tickets.forEach(ticket -> {
            if (!ticket.isAvailable()) {
                throw new AppException(ErrorCode.TICKET_NOT_AVAILABLE);
            }
            ticket.setAvailable(false);
            ticket.setBooking(booking);
        });

        bookingRepository.save(booking);
        return bookingMapper.toBookingResponse(booking);
    }

    //Update booking
    public BookingResponse updateBookingStatus(String id, BookingStatusEnum status) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOT_FOUND));
        if (booking.getBookingStatusEnum() == BookingStatusEnum.CANCELLED) {
            throw new AppException(ErrorCode.BOOKING_ALREADY_CANCELLED);
        }
        if (booking.getBookingStatusEnum() == status) {
            throw new AppException(ErrorCode.BOOKING_CANNOT_BE_SET);
        }
        booking.setBookingStatusEnum(status);
        if (booking.getBookingStatusEnum() == BookingStatusEnum.CANCELLED) {
            booking.getTickets().forEach(ticket -> ticket.setAvailable(true));
        }

        bookingRepository.save(booking);
        return bookingMapper.toBookingResponse(booking);
    }

    //Delete booking
    public String deleteBooking(String id) {
        if (!bookingRepository.existsById(id)) {
            throw new AppException(ErrorCode.BOOKING_NOT_FOUND);
        }

        bookingRepository.deleteById(id);
        return "Delete booking successfully";
    }
}
