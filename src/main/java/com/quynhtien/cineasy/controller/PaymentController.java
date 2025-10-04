package com.quynhtien.cineasy.controller;

import com.quynhtien.cineasy.dto.request.PaymentCreationRequest;
import com.quynhtien.cineasy.dto.request.PaymentUpdateRequest;
import com.quynhtien.cineasy.dto.response.ApiResponse;
import com.quynhtien.cineasy.dto.response.PaymentResponse;
import com.quynhtien.cineasy.service.PaymentService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping("/payments")
public class PaymentController {
    PaymentService paymentService;

    //Get all payments
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ApiResponse<List<PaymentResponse>> findAll() {
        return ApiResponse.<List<PaymentResponse>>builder()
                .result(paymentService.getPayments())
                .build();
    }

    //Get payment by id
    @GetMapping("/{id}")
    public ApiResponse<PaymentResponse> findAll(@PathVariable String id) {
        return ApiResponse.<PaymentResponse>builder()
                .result(paymentService.getPayment(id))
                .build();
    }

    //Create payment
    @PostMapping
    public ApiResponse<PaymentResponse> createPayment(@RequestBody @Valid PaymentCreationRequest request) {
        return ApiResponse.<PaymentResponse>builder()
                .result(paymentService.createPayment(request))
                .build();
    }

    //Update payment
    @PutMapping("/{id}")
    public ApiResponse<PaymentResponse> updatePayment(@PathVariable String id, @RequestBody @Valid PaymentUpdateRequest request) {
        return ApiResponse.<PaymentResponse>builder()
                .result(paymentService.updatePaymentStatus(id, request))
                .build();
    }

    //Delete payment
    /*@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ApiResponse<String> deletePayment(@PathVariable String id) {
        return ApiResponse.<String>builder()
                .result(paymentService.deletePayment(id))
                .build();
    }*/
}
