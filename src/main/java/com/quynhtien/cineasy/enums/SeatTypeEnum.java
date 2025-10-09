package com.quynhtien.cineasy.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
@AllArgsConstructor
public enum SeatTypeEnum {
    REGULAR(80),
    VIP(120),
    COUPLE(110);

    int price;
}
