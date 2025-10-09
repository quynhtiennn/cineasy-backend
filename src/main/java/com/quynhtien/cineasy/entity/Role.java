package com.quynhtien.cineasy.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
public class Role {
    @Id
    @Column(unique = true, nullable = false)
    String name;

    String description;

    @ManyToMany
    List<Permission> permissions;


}
