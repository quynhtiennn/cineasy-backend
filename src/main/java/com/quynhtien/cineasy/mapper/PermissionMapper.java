package com.quynhtien.cineasy.mapper;

import com.quynhtien.cineasy.dto.request.PermissionRequest;
import com.quynhtien.cineasy.dto.request.RoleRequest;
import com.quynhtien.cineasy.dto.response.PermissionResponse;
import com.quynhtien.cineasy.dto.response.RoleResponse;
import com.quynhtien.cineasy.entity.Permission;
import com.quynhtien.cineasy.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);

    void updatePermission(PermissionRequest request, @MappingTarget Permission permission);
}

