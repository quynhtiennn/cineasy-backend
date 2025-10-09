package com.quynhtien.cineasy.entity.base;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
@SuperBuilder
@MappedSuperclass
public abstract class BaseUUIDEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;
}
