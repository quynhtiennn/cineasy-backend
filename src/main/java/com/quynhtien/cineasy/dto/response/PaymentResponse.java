package com.quynhtien.cineasy.dto.response;

import com.quynhtien.cineasy.enums.PaymentMethod;
import com.quynhtien.cineasy.enums.PaymentStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentResponse {
    String id;
    PaymentMethod method;
    double amount;
    LocalDateTime paymentDate;
    PaymentStatus status;
}
