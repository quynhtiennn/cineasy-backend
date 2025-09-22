package com.quynhtien.cineasy.mapper;

import com.quynhtien.cineasy.dto.request.PaymentCreationRequest;
import com.quynhtien.cineasy.dto.request.PaymentUpdateRequest;
import com.quynhtien.cineasy.dto.response.PaymentResponse;
import com.quynhtien.cineasy.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PaymentMapper {
    @Mapping(target = "booking", ignore = true)
    Payment toPayment(PaymentCreationRequest request);

    PaymentResponse toPaymentResponse(Payment payment);

    void updatePayment(PaymentUpdateRequest request, @MappingTarget Payment payment);
}

