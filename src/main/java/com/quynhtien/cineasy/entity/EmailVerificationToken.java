package com.quynhtien.cineasy.entity;

import com.quynhtien.cineasy.entity.base.BaseUUIDEntity;
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
public class EmailVerificationToken extends BaseUUIDEntity {

    @OneToOne(fetch = FetchType.EAGER)
    User user;

    LocalDateTime expiryTime;


}
