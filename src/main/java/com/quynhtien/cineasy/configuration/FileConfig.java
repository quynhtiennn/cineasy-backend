package com.quynhtien.cineasy.configuration;

import com.quynhtien.cineasy.repository.AzureFileRepository;
import com.quynhtien.cineasy.repository.FileRepository;
import com.quynhtien.cineasy.repository.LocalStackRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Configuration
public class FileConfig {
    @Value("${storage.provider}")
    @NonFinal
    String provider;

    LocalStackRepository localstackRepository;

    AzureFileRepository azureFileRepository;

    @Bean
    public FileRepository fileRepository() {
        if ("azure".equalsIgnoreCase(provider)) {
            return azureFileRepository;
        } else {
            return localstackRepository;
        }
    }
}
