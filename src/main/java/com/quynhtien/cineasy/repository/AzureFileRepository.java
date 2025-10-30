package com.quynhtien.cineasy.repository;

import com.azure.storage.blob.*;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.quynhtien.cineasy.entity.FileInfo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Repository
public class AzureFileRepository implements FileRepository {

    BlobContainerClient containerClient;

    @Override
    public FileInfo uploadFile(MultipartFile file, String ownerId) throws IOException {
        String fileExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String fileName = Objects.isNull(fileExtension)
                ? UUID.randomUUID().toString()
                : UUID.randomUUID() + "." + fileExtension;

        BlobClient blobClient = containerClient.getBlobClient(fileName);
        blobClient.upload(file.getInputStream(), file.getSize(), true);

        blobClient.setHttpHeaders(new BlobHttpHeaders().setContentType(file.getContentType()));

        return FileInfo.builder()
                .id(fileName)
                .ownerId(ownerId)
                .contentType(file.getContentType())
                .md5Checksum(DigestUtils.md5DigestAsHex(file.getInputStream()))
                .size(file.getSize())
                .url(blobClient.getBlobUrl())
                .build();
    }

    @Override
    public Resource downloadFile(String fileName) {
        BlobClient blobClient = containerClient.getBlobClient(fileName);
        try {
            return new UrlResource(blobClient.getBlobUrl());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid blob URL", e);
        }
    }

    @Override
    public String deleteFile(String fileName) {
        containerClient.getBlobClient(fileName).deleteIfExists();
        return "File deleted successfully from Azure.";
    }
}
