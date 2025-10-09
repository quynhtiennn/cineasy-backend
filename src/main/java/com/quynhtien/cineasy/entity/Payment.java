package com.quynhtien.cineasy.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.quynhtien.cineasy.entity.base.BaseUUIDEntity;
import com.quynhtien.cineasy.enums.PaymentMethodEnum;
import com.quynhtien.cineasy.enums.PaymentStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
public class Payment extends BaseUUIDEntity {

    PaymentMethodEnum method;
    double amount;
    LocalDateTime paymentDate;
    PaymentStatusEnum status;

    @OneToOne
    @JoinColumn(name = "booking_id")
    @JsonBackReference
    Booking booking;



}
