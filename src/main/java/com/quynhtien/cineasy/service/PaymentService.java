package com.quynhtien.cineasy.service;

import com.quynhtien.cineasy.dto.request.PaymentCreationRequest;
import com.quynhtien.cineasy.dto.request.PaymentUpdateRequest;
import com.quynhtien.cineasy.dto.response.PaymentResponse;
import com.quynhtien.cineasy.entity.Booking;
import com.quynhtien.cineasy.entity.Payment;
import com.quynhtien.cineasy.enums.BookingStatus;
import com.quynhtien.cineasy.enums.PaymentStatus;
import com.quynhtien.cineasy.exception.AppException;
import com.quynhtien.cineasy.exception.ErrorCode;
import com.quynhtien.cineasy.mapper.PaymentMapper;
import com.quynhtien.cineasy.repository.BookingRepository;
import com.quynhtien.cineasy.repository.PaymentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class PaymentService {
    PaymentRepository paymentRepository;
    PaymentMapper paymentMapper;
    BookingRepository bookingRepository;

    //Get all payments
    public List<PaymentResponse> getPayments() {
        return paymentRepository.findAll().stream()
                .map(paymentMapper::toPaymentResponse).toList();
    }

    //Get payment by id
    public PaymentResponse getPayment(String id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PAYMENT_NOT_FOUND));
        return paymentMapper.toPaymentResponse(payment);
    }

    //Create payment
    public PaymentResponse createPayment(PaymentCreationRequest request) {
        Booking booking = bookingRepository.findById(request.getBookingId())
                .orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOT_FOUND));

        Payment payment = Payment.builder()
                .paymentDate(LocalDateTime.now())
                .method(request.getMethod())
                .amount(booking.getTotalPrice())
                .status(PaymentStatus.PENDING)
                .booking(booking)
                .build();

        paymentRepository.save(payment);
        return paymentMapper.toPaymentResponse(payment);
    }

    //Update payment
    public PaymentResponse updatePaymentStatus(String id, PaymentUpdateRequest request) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PAYMENT_NOT_FOUND));
        if (payment.getStatus() == PaymentStatus.FAILED ||
                payment.getStatus() == PaymentStatus.REFUNDED) {
            throw new AppException(ErrorCode.PAYMENT_CANNOT_BE_SET);
        }
        if (payment.getStatus() == request.getPaymentStatus()) {
            throw new AppException(ErrorCode.PAYMENT_CANNOT_BE_SET);
        }
        payment.setStatus(request.getPaymentStatus());
        if (payment.getStatus() == PaymentStatus.SUCCESSFULLY) {
            payment.getBooking().setBookingStatus(BookingStatus.CONFIRMED);
        }
        else if (payment.getStatus() == PaymentStatus.FAILED ||
                payment.getStatus() == PaymentStatus.REFUNDED) {
            payment.getBooking().setBookingStatus(BookingStatus.CANCELLED);
            payment.getBooking().getTickets().forEach(ticket -> ticket.setAvailable(true));
        }

        paymentRepository.save(payment);
        return paymentMapper.toPaymentResponse(payment);
    }

    //Delete payment
    public String deletePayment(String id) {
        if (!paymentRepository.existsById(id)) {
            throw new AppException(ErrorCode.PAYMENT_NOT_FOUND);
        }
        paymentRepository.deleteById(id);
        return "Delete payment successfully";
    }
}
