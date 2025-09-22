package com.quynhtien.cineasy.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@AllArgsConstructor
public enum BookingStatus {
    PENDING,
    CONFIRMED,
    CANCELLED;

}
