package com.quynhtien.cineasy.service;

import com.quynhtien.cineasy.dto.response.FileInfoResponse;
import com.quynhtien.cineasy.dto.response.FileResponse;
import com.quynhtien.cineasy.entity.FileInfo;
import com.quynhtien.cineasy.exception.AppException;
import com.quynhtien.cineasy.exception.ErrorCode;
import com.quynhtien.cineasy.mapper.FileInfoMapper;
import com.quynhtien.cineasy.repository.FileInfoRepository;
import com.quynhtien.cineasy.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class FileService {

    FileInfoRepository fileInfoRepository;
    FileRepository fileRepository;
    FileInfoMapper fileInfoMapper;

    public List<FileInfoResponse> getAllFileInfo() {
        List<FileInfo> fileInfos = fileInfoRepository.findAll();
        return fileInfos.stream().map(fileInfoMapper::toFileInfoResponse).collect(Collectors.toList());
    }

    public FileInfoResponse uploadFile(MultipartFile file) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String ownerId = ((Jwt) auth.getPrincipal()).getClaim("userId");
        FileInfo fileInfo = fileRepository.uploadFile(file, ownerId);
        fileInfoRepository.save(fileInfo);

        return fileInfoMapper.toFileInfoResponse(fileInfo);
    }

    public FileResponse downloadFile(String fileName) {
        log.info("Downloading file service: {}", fileName);

        FileInfo fileInfo = fileInfoRepository.findById(fileName).orElseThrow(
                () -> new AppException(ErrorCode.FILE_NOT_FOUND));

        Resource file =fileRepository.downloadFile(fileName);

        return new FileResponse(fileInfo.getContentType(), file);
    }

    public String deleteFile(String fileName) {
        if(!fileInfoRepository.existsById(fileName)) {
            throw new AppException(ErrorCode.FILE_NOT_FOUND);
        }

        fileInfoRepository.deleteById(fileName);

        return fileRepository.deleteFile(fileName);
    }
}