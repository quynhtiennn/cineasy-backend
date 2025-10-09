package com.quynhtien.cineasy.dto.request;

import com.quynhtien.cineasy.enums.PaymentStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentUpdateRequest {
    @NotNull(message = "Payment status is required")
    PaymentStatusEnum status;
}
