package com.quynhtien.cineasy.controller;

import com.quynhtien.cineasy.dto.request.UserCreationRequest;
import com.quynhtien.cineasy.dto.request.UserUpdateRequest;
import com.quynhtien.cineasy.dto.response.ApiResponse;
import com.quynhtien.cineasy.dto.response.AuthenticationResponse;
import com.quynhtien.cineasy.dto.response.UserResponse;
import com.quynhtien.cineasy.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    UserService userService;

    //Get all users
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ApiResponse<List<UserResponse>> findAll() {
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getUsers())
                .build();
    }

    //Get user by id
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ApiResponse<UserResponse> findUserById(@PathVariable UUID id) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUser(id))
                .build();
    }

    //Get myinfo by id
    @GetMapping("/myInfo")
    public ApiResponse<UserResponse> getMyInfo() {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }

    //Create user
    @PostMapping
    public ApiResponse<String> createUser(@RequestBody @Valid UserCreationRequest request) {
        return ApiResponse.<String>builder()
                .result(userService.createUser(request))
                .build();
    }

    //Update user
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.claims['userId']")
    @PutMapping("/{id}")
    public ApiResponse<UserResponse> updateUser(@PathVariable UUID id, @RequestBody @Valid UserUpdateRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(id, request))
                .build();
    }

    //Delete user
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.claims['userId'] or hasAuthority('DELETE_USER')")
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteUser(@PathVariable UUID id) {
        return ApiResponse.<String>builder()
                .result(userService.deleteUser(id))
                .build();
    }

}
