package com.quynhtien.cineasy.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults
@Getter
@AllArgsConstructor
public enum SeatType {
    REGULAR(80),
    VIP(120),
    COUPLE(110);

    int price;
}
