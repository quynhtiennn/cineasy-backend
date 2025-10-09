package com.quynhtien.cineasy.dto.response;

import com.quynhtien.cineasy.enums.PaymentMethodEnum;
import com.quynhtien.cineasy.enums.PaymentStatusEnum;
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
    PaymentMethodEnum method;
    double amount;
    LocalDateTime paymentDate;
    PaymentStatusEnum status;
}
