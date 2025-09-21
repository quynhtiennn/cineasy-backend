package com.quynhtien.cineasy.entity;

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
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String method; // CREDIT_CARD, PAYPAL, MOMO
    double amount;
    LocalDateTime paymentDate;
    String status; // PENDING, SUCCESS, FAILED, REFUNDED

    /*@OneToOne
    Booking booking;*/




}
