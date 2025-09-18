package com.quynhtien.cineasy.controller;

import com.quynhtien.cineasy.dto.request.PermissionRequest;
import com.quynhtien.cineasy.dto.response.ApiResponse;
import com.quynhtien.cineasy.dto.response.PermissionResponse;
import com.quynhtien.cineasy.service.PermissionService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping("/permissions")
public class PermissionController {
    PermissionService permissionService;

    //Get all permissions
    @GetMapping
    public ApiResponse<List<PermissionResponse>> findAll() {
        return ApiResponse.<List<PermissionResponse>>builder()
                .result(permissionService.getPermissions())
                .build();
    }

    //Create permission
    @PostMapping
    public ApiResponse<PermissionResponse> createPermission(@RequestBody @Valid PermissionRequest request) {
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.createPermission(request))
                .build();
    }

    //Update permission
    @PutMapping
    public ApiResponse<PermissionResponse> updatePermission(@RequestBody @Valid PermissionRequest request) {
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.updatePermission(request))
                .build();
    }

    //Delete permission
    @DeleteMapping("/{id}")
    public ApiResponse<String> deletePermission(@PathVariable String id) {
        return ApiResponse.<String>builder()
                .result(permissionService.deletePermission(id))
                .build();
    }
}
