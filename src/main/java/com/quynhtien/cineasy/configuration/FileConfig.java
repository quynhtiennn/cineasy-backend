package com.quynhtien.cineasy.configuration;

import com.quynhtien.cineasy.repository.AzureFileRepository;
import com.quynhtien.cineasy.repository.FileRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Configuration
public class FileConfig {
    AzureFileRepository azureFileRepository;

    @Bean
    public FileRepository fileRepository() {
        return azureFileRepository;
    }
}
