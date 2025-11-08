package com.quynhtien.cineasy.controller;


import com.quynhtien.cineasy.dto.response.ApiResponse;
import com.quynhtien.cineasy.dto.response.FileInfoResponse;
import com.quynhtien.cineasy.dto.response.FileResponse;
import com.quynhtien.cineasy.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE,makeFinal = true)
public class FileController {

    FileService fileService;

    @GetMapping
    public ApiResponse<List<FileInfoResponse>> getAllFileInfo() {
        return ApiResponse.<List<FileInfoResponse>>builder()
                .result(fileService.getAllFileInfo())
                .build();
    }

    @PostMapping("/upload")
    public ApiResponse<FileInfoResponse> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return ApiResponse.<FileInfoResponse>builder()
                .result(fileService.uploadFile(file))
                .build();
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        log.info("Downloading file: {}", fileName);
        FileResponse fileResponse = fileService.downloadFile(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, fileResponse.contentType())
                .body(fileResponse.resource());
    }

    @DeleteMapping("delete/{fileName}")
    public ApiResponse<String> deleteFile(@PathVariable String fileName) {
        return ApiResponse.<String>builder()
                .result(fileService.deleteFile(fileName))
                .build();
    }
}

