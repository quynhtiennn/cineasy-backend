package com.quynhtien.cineasy.mapper;

import com.quynhtien.cineasy.dto.response.FileInfoResponse;
import com.quynhtien.cineasy.entity.FileInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileInfoMapper {
    FileInfoResponse toFileInfoResponse(FileInfo fileInfo);

}

