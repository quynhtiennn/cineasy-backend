package com.quynhtien.cineasy.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
public class FileInfo {
    @Id
    String id;
    String ownerId;
    String contentType;
    long size;
    String md5Checksum;
    String url;

}
