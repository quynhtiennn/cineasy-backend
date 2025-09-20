package com.quynhtien.cineasy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoggedOutToken {
    @Id
    @Column(unique = true, nullable = false)
    String tokenId;

    String expirationTime;
}
