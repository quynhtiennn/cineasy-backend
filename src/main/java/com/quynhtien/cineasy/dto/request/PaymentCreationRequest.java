package com.quynhtien.cineasy.dto.request;

import com.quynhtien.cineasy.enums.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentCreationRequest {
    @NotBlank(message = "Payment method is required")
    PaymentMethod method;

    @NotBlank(message = "Booking ID is required")
    String bookingId;

}
