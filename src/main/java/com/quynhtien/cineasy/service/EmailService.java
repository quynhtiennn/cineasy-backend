package com.quynhtien.cineasy.service;

import com.quynhtien.cineasy.dto.request.EmailRequest;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@Service
public class EmailService {

    @Value("${mail.server.url}")
    @NonFinal
    String mailServerUrl;

    @Value("${mail.server.api-key}")
    @NonFinal
    String apiKey;

    RestTemplate restTemplate = new RestTemplate();

    public void sendVerificationEmail(String to, UUID token) {
        EmailRequest emailRequest = new EmailRequest(to, token);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("API-KEY", apiKey);

        HttpEntity<EmailRequest> entity = new HttpEntity<>(emailRequest, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    mailServerUrl + "/send-verification-email",
                    HttpMethod.POST,
                    entity,
                    String.class
            );
            log.info("Mail server response: {}", response.getBody());
        } catch (Exception e) {
            log.error("Failed to call mail server: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to send verification email", e);
        }
    }

    public void sendResetPasswordEmail(String to, UUID token) {
        EmailRequest emailRequest = new EmailRequest(to, token);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("API-KEY", apiKey);

        HttpEntity<EmailRequest> entity = new HttpEntity<>(emailRequest, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    mailServerUrl + "/send-reset-password-email",
                    HttpMethod.POST,
                    entity,
                    String.class
            );
            log.info("Mail server response: {}", response.getBody());
        } catch (Exception e) {
            log.error("Failed to call mail server: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to send verification email", e);
        }
    }
}

