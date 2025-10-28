package com.quynhtien.cineasy.dto.request;

import com.quynhtien.cineasy.enums.BookingStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingUpdateRequest {
    @NotNull(message = "Status is required")
    BookingStatusEnum status;

}
