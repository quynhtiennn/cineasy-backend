package com.quynhtien.cineasy.controller;

import com.quynhtien.cineasy.dto.request.*;
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
    public ApiResponse<AuthenticationResponse> refresh(@RequestBody @Valid TokenRequest request) {
        return ApiResponse.<AuthenticationResponse>builder()
                .result(authenticationService.refreshToken(request))
                .build();
    }

    @PostMapping("/verify-email")
    public ApiResponse<AuthenticationResponse> verifyEmail(@RequestBody @Valid TokenRequest request) {
        return ApiResponse.<AuthenticationResponse>builder()
                .result(authenticationService.verifyEmailVerificationToken(request))
                .build();
    }

    @PostMapping("/resend-verification-email")
    public ApiResponse<String> verifyEmail(@RequestBody @Valid ResendVerificationEmailRequest request) {
        return ApiResponse.<String>builder()
                .result(authenticationService.resendEmailVerificationToken(request))
                .build();
    }

    @PostMapping("/forgot-password")
    public ApiResponse<String> forgotPassword(@RequestBody @Valid ForgotPasswordRequest request) {
        return ApiResponse.<String>builder()
                .result(authenticationService.sendResetPasswordEmail(request))
                .build();
    }

    @PostMapping("/verify-password-reset-token")
    public ApiResponse<Boolean> verifyPasswordResetToken(@RequestBody @Valid TokenRequest request) {
        return ApiResponse.<Boolean>builder()
                .result(authenticationService.verifyPasswordResetToken(request))
                .build();
    }

    @PostMapping("/reset-password")
    public ApiResponse<String> resetPassword(@RequestBody @Valid ResetPasswordRequest request) {
        return ApiResponse.<String>builder()
                .result(authenticationService.resetPassword(request))
                .build();
    }

}
