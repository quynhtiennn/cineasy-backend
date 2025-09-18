package com.quynhtien.cineasy.service;

import com.quynhtien.cineasy.dto.request.PermissionRequest;
import com.quynhtien.cineasy.dto.response.PermissionResponse;
import com.quynhtien.cineasy.entity.Permission;
import com.quynhtien.cineasy.exception.AppException;
import com.quynhtien.cineasy.exception.ErrorCode;
import com.quynhtien.cineasy.mapper.PermissionMapper;
import com.quynhtien.cineasy.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    //Get all permissions
    public List<PermissionResponse> getPermissions() {
        return permissionRepository.findAll().stream()
                .map(permissionMapper::toPermissionResponse).toList();
    }

    //Create permission
    public PermissionResponse createPermission(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    //Update permission
    public PermissionResponse updatePermission(PermissionRequest request) {
        Permission permission = permissionRepository.findById(request.getName())
                .orElseThrow(() -> new AppException(ErrorCode.PERMISSION_NOT_FOUND));

        permissionMapper.updatePermission(request, permission);
        permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    //Delete permission
    public String deletePermission(String name) {
        if (!permissionRepository.existsById(name)) {
            throw new AppException(ErrorCode.PERMISSION_NOT_FOUND);
        }
        permissionRepository.deleteById(name);
        return "Delete permission successfully";
    }
}
