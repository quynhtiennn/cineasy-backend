package com.quynhtien.cineasy.dto.response;

import com.quynhtien.cineasy.entity.Booking;
import com.quynhtien.cineasy.entity.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String username;
    String email;
    String firstName;
    String lastName;

    Set<Role> roles;

    Set<Booking> bookings;
}
