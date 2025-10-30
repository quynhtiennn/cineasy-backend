package com.quynhtien.cineasy.repository;

import com.quynhtien.cineasy.entity.FileInfo;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@Repository
public class LocalStackRepository implements FileRepository {
    S3Client s3Client;

    @NonFinal
    @Value("${app.file.downloadPrefix}")
    String urlPrefix;

    @NonFinal
    @Value("${aws.s3.bucketName}")
    protected String bucketName;

    @PostConstruct
    private void createBucketIfNotExists() {
        try {
            s3Client.createBucket(CreateBucketRequest.builder()
                    .bucket(bucketName)
                    .build());
            log.info("Created bucket: " + bucketName);
        } catch (Exception e) {
            log.error("Bucket creation error: " + e.getMessage());
        }
    }

    @Override
    public FileInfo uploadFile(MultipartFile file, String ownerId) throws IOException {
        String fileExtension = StringUtils
                .getFilenameExtension(file.getOriginalFilename());
        String fileName = Objects.isNull(fileExtension)
                ? UUID.randomUUID().toString()
                : UUID.randomUUID() + "." + fileExtension;

        s3Client.putObject(
                PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(fileName)
                        .build(),
                RequestBody.fromBytes(file.getBytes())
        );

        return FileInfo.builder()
                .id(fileName)
                .ownerId(ownerId)
                .contentType(file.getContentType())
                .md5Checksum(DigestUtils.md5DigestAsHex(file.getInputStream()))
                .size(file.getSize())
                .url(urlPrefix + fileName)
                .build();
    }

    @Override
    public Resource downloadFile(String fileName) {
        GetObjectRequest getRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();

        ResponseBytes<GetObjectResponse> objectBytes =
                s3Client.getObjectAsBytes(getRequest);

        return new ByteArrayResource(objectBytes.asByteArray());
    }

    @Override
    public String deleteFile(String fileName) {
        DeleteObjectRequest deleteRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();

        s3Client.deleteObject(deleteRequest);

        return "File deleted successfully.";
    }
}
