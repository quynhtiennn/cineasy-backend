package com.quynhtien.cineasy.controller;

import com.quynhtien.cineasy.dto.request.AuthenticationIntrospectRequest;
import com.quynhtien.cineasy.dto.request.AuthenticationLoginRequest;
import com.quynhtien.cineasy.dto.response.ApiResponse;
import com.quynhtien.cineasy.dto.response.AuthenticationIntrospectResponse;
import com.quynhtien.cineasy.dto.response.AuthenticationLoginResponse;
import com.quynhtien.cineasy.entity.User;
import com.quynhtien.cineasy.service.AuthenticationService;
import com.quynhtien.cineasy.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/login")
    public ApiResponse<AuthenticationLoginResponse> login(@RequestBody AuthenticationLoginRequest request) {
        return ApiResponse.<AuthenticationLoginResponse>builder()
                .result(authenticationService.login(request))
                .build();
    }

    @PostMapping("/introspect")
    public ApiResponse<AuthenticationIntrospectResponse> login(@RequestBody AuthenticationIntrospectRequest request) {
        return ApiResponse.<AuthenticationIntrospectResponse>builder()
                .result(authenticationService.introspectToken(request))
                .build();
    }
}
