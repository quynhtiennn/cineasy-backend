package com.quynhtien.cineasy.service;

import com.quynhtien.cineasy.dto.request.RoleRequest;
import com.quynhtien.cineasy.dto.response.RoleResponse;
import com.quynhtien.cineasy.entity.Permission;
import com.quynhtien.cineasy.entity.Role;
import com.quynhtien.cineasy.exception.AppException;
import com.quynhtien.cineasy.exception.ErrorCode;
import com.quynhtien.cineasy.mapper.RoleMapper;
import com.quynhtien.cineasy.repository.PermissionRepository;
import com.quynhtien.cineasy.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class RoleService {
    RoleRepository roleRepository;
    RoleMapper roleMapper;
    PermissionRepository permissionRepository;

    //Get all roles
    public List<RoleResponse> getRoles() {
        return roleRepository.findAll().stream()
                .map(roleMapper::toRoleResponse).toList();
    }

    //Create role
    public RoleResponse createRole(RoleRequest request) {
        Role role = roleMapper.toRole(request);

        //manually map permissions
        List<Permission> permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    //Update user
    public RoleResponse updateRole(RoleRequest request) {
        Role role = roleRepository.findById(request.getName())
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));

        roleMapper.updateRole(request, role);

        //manually map permissions
        List<Permission> permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    //Delete user
    public String deleteRole(String name) {
        if (!roleRepository.existsById(name)) {
            throw new AppException(ErrorCode.ROLE_NOT_FOUND);
        }
        roleRepository.deleteById(name);
        return "Delete user successfully";
    }
}
