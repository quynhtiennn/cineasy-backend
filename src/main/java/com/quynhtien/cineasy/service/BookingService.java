package com.quynhtien.cineasy.service;

import com.quynhtien.cineasy.dto.request.BookingRequest;
import com.quynhtien.cineasy.dto.request.BookingUpdateRequest;
import com.quynhtien.cineasy.dto.request.PaymentCreationRequest;
import com.quynhtien.cineasy.dto.response.BookingResponse;
import com.quynhtien.cineasy.entity.Booking;
import com.quynhtien.cineasy.entity.Ticket;
import com.quynhtien.cineasy.entity.User;
import com.quynhtien.cineasy.enums.BookingStatusEnum;
import com.quynhtien.cineasy.enums.PaymentMethodEnum;
import com.quynhtien.cineasy.enums.PaymentStatusEnum;
import com.quynhtien.cineasy.exception.AppException;
import com.quynhtien.cineasy.exception.ErrorCode;
import com.quynhtien.cineasy.mapper.BookingMapper;
import com.quynhtien.cineasy.repository.BookingRepository;
import com.quynhtien.cineasy.repository.TicketRepository;
import com.quynhtien.cineasy.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class BookingService {
    BookingRepository bookingRepository;
    BookingMapper bookingMapper;
    TicketRepository ticketRepository;
    PaymentService paymentService;
    UserRepository userRepository;

    //Get all bookings
    public List<BookingResponse> getBookings() {
        return bookingRepository.findAll().stream()
                .map(bookingMapper::toBookingResponse).toList();
    }

    //Get booking by id
    public BookingResponse getBooking(UUID id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOT_FOUND));
        return bookingMapper.toBookingResponse(booking);
    }

    //Create booking
    public BookingResponse createBooking(BookingRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();

        User user = userRepository.findByUsername(userName)
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

        PaymentCreationRequest paymentCreationRequest = PaymentCreationRequest.builder()
                .bookingId(booking.getId())
                .method(PaymentMethodEnum.CREDIT_CARD)
                .build();
        paymentService.createPayment(paymentCreationRequest);

        return bookingMapper.toBookingResponse(booking);
    }

    //Update booking
    public BookingResponse updateBookingStatus(UUID id, BookingUpdateRequest request) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOT_FOUND));
        if (booking.getBookingStatusEnum() == BookingStatusEnum.CANCELLED) {
            throw new AppException(ErrorCode.BOOKING_ALREADY_CANCELLED);
        }

        if (booking.getBookingStatusEnum() == request.getStatus()) {
            throw new AppException(ErrorCode.BOOKING_CANNOT_BE_SET);
        }

        if (booking.getBookingStatusEnum() == BookingStatusEnum.PENDING) {
            if (request.getStatus() == BookingStatusEnum.PAID) {
                booking.getPayment().setStatus(PaymentStatusEnum.PAID);
                booking.setBookingStatusEnum(request.getStatus());
            } else if (request.getStatus() == BookingStatusEnum.CANCELLED) {
                booking.getPayment().setStatus(PaymentStatusEnum.FAILED);
                booking.setBookingStatusEnum(request.getStatus());
            }
        }

        if (booking.getBookingStatusEnum() == BookingStatusEnum.PAID) {
            if (request.getStatus() == BookingStatusEnum.CANCELLED) {
                booking.getPayment().setStatus(PaymentStatusEnum.REFUNDED);
                booking.setBookingStatusEnum(request.getStatus());
            } else if (request.getStatus() == BookingStatusEnum.PENDING) {
                throw new AppException(ErrorCode.BOOKING_CANNOT_BE_SET);
            }
        }

        if (booking.getBookingStatusEnum() == BookingStatusEnum.CANCELLED) {
            booking.getTickets().forEach(ticket -> ticket.setAvailable(true));
        }

        bookingRepository.save(booking);
        return bookingMapper.toBookingResponse(booking);
    }

    //Delete booking
    public String deleteBooking(UUID id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOT_FOUND));

        List<Ticket> tickets = booking.getTickets();
        tickets.forEach(ticket -> ticket.setAvailable(true));
        ticketRepository.saveAll(tickets);

        bookingRepository.deleteById(id);
        return "Delete booking successfully";
    }
}
