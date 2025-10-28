package com.quynhtien.cineasy.dto.request;

import com.quynhtien.cineasy.enums.PaymentMethodEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentCreationRequest {
    @NotNull(message = "Payment method is required")
    PaymentMethodEnum method;

    @NotBlank(message = "Booking ID is required")
    UUID bookingId;

}
