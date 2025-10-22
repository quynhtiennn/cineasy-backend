package com.quynhtien.cineasy.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FileInfoResponse {
    String id;
    String ownerId;
    String contentType;
    String url;
}
