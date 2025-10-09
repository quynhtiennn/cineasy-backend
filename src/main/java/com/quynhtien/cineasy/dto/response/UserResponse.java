package com.quynhtien.cineasy.dto.response;

import com.quynhtien.cineasy.entity.Booking;
import com.quynhtien.cineasy.entity.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    UUID id;
    String username;
    String email;
    String firstName;
    String lastName;

    List<Role> roles;

    List<Booking> bookings;
}
