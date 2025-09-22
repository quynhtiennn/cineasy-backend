package com.quynhtien.cineasy.dto.request;

import com.quynhtien.cineasy.enums.PaymentMethod;
import com.quynhtien.cineasy.enums.PaymentStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentUpdateRequest {
    @NotBlank(message = "Payment status is required")
    PaymentStatus paymentStatus;
}
