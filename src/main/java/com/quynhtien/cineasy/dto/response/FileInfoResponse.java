package com.quynhtien.cineasy.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.quynhtien.cineasy.entity.ShowTime;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

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
