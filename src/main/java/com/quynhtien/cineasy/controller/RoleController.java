package com.quynhtien.cineasy.controller;

import com.quynhtien.cineasy.dto.request.RoleRequest;
import com.quynhtien.cineasy.dto.response.ApiResponse;
import com.quynhtien.cineasy.dto.response.RoleResponse;
import com.quynhtien.cineasy.service.RoleService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping("/roles")
public class RoleController {
    RoleService roleService;

    //Get all roles
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ApiResponse<List<RoleResponse>> findAll() {
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getRoles())
                .build();
    }

    //Create role
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ApiResponse<RoleResponse> createRole(@RequestBody @Valid RoleRequest request) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.createRole(request))
                .build();
    }

    //Update role
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ApiResponse<RoleResponse> updateRole(@RequestBody @Valid RoleRequest request) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.updateRole(request))
                .build();
    }

    //Delete role
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{name}")
    public ApiResponse<String> deleteRole(@PathVariable String name) {
        return ApiResponse.<String>builder()
                .result(roleService.deleteRole(name))
                .build();
    }
}
