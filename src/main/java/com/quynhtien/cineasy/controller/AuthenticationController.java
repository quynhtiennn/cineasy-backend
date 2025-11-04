package com.quynhtien.cineasy.controller;

import com.quynhtien.cineasy.dto.request.ResendVerificationEmailRequest;
import com.quynhtien.cineasy.dto.request.TokenRequest;
import com.quynhtien.cineasy.dto.request.AuthenticationRequest;
import com.quynhtien.cineasy.dto.response.ApiResponse;
import com.quynhtien.cineasy.dto.response.IntrospectResponse;
import com.quynhtien.cineasy.dto.response.AuthenticationResponse;
import com.quynhtien.cineasy.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest request) {
        log.info("Login attempt for user: {}", request.getUsername());
        return ApiResponse.<AuthenticationResponse>builder()
                .result(authenticationService.login(request))
                .build();
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> introspect(@RequestBody @Valid TokenRequest request) {
        return ApiResponse.<IntrospectResponse>builder()
                .result(authenticationService.introspectToken(request))
                .build();
    }

    @PostMapping("/logout")
    public ApiResponse<String> logout(@RequestBody @Valid TokenRequest request) {
        return ApiResponse.<String>builder()
                .result(authenticationService.logout(request))
                .build();
    }

    @PostMapping("/refresh")
    public ApiResponse<AuthenticationResponse> refresh(@RequestBody TokenRequest request) {
        return ApiResponse.<AuthenticationResponse>builder()
                .result(authenticationService.refreshToken(request))
                .build();
    }

    @PostMapping("/verifyEmail")
    public ApiResponse<AuthenticationResponse> verifyEmail(@RequestBody TokenRequest request) {
        return ApiResponse.<AuthenticationResponse>builder()
                .result(authenticationService.verifyEmailVerificationToken(request))
                .build();
    }

    @PostMapping("/resendVerificationEmail")
    public ApiResponse<String> verifyEmail(@RequestBody @Valid ResendVerificationEmailRequest request) {
        return ApiResponse.<String>builder()
                .result(authenticationService.resendEmailVerificationToken(request))
                .build();
    }
}
