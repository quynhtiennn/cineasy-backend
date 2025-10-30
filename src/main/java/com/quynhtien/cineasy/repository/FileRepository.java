package com.quynhtien.cineasy.repository;

import com.quynhtien.cineasy.entity.FileInfo;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileRepository {
    FileInfo uploadFile(MultipartFile file, String ownerId) throws IOException;
    Resource downloadFile(String fileName);
    String deleteFile(String fileName);
}
