package com.quynhtien.cineasy.configuration;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class BlobClientConfig {
    @Value("${azure.blob.connection-string}")
    String connectionString;

    @Value("${azure.storage.container-name}")
    String containerName;

    @Bean
    public BlobContainerClient blobContainerClient() {

        BlobServiceClient serviceClient = new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();

        BlobContainerClient containerClient = serviceClient.getBlobContainerClient(containerName);
        if (!containerClient.exists()) {
            containerClient.create();
        }
        return containerClient;
    }
}
