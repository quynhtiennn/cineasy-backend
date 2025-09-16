package com.quynhtien.cineasy.mapper;

import com.quynhtien.cineasy.dto.request.UserCreationRequest;
import com.quynhtien.cineasy.dto.request.UserUpdateRequest;
import com.quynhtien.cineasy.dto.response.UserResponse;
import com.quynhtien.cineasy.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    UserResponse toUserResponse(User user);

    void updateUser(UserUpdateRequest request, @MappingTarget User user);
}

