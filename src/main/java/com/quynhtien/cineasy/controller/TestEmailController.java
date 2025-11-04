package com.quynhtien.cineasy.controller;


import com.quynhtien.cineasy.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class TestEmailController {
    EmailService emailService;

    @GetMapping("/test-email")
    public String testEmail() {
        emailService.sendVerificationEmail("chefathome1209@gmail.com", UUID.fromString("d7f3c23d-284e-473d-aec3-1f886424431d"));
        return "Email sent!";
    }
}
