package com.quynhtien.cineasy.service;

import com.quynhtien.cineasy.dto.request.AuthenticationRequest;
import com.quynhtien.cineasy.dto.request.UserCreationRequest;
import com.quynhtien.cineasy.dto.request.UserUpdateRequest;
import com.quynhtien.cineasy.dto.response.AuthenticationResponse;
import com.quynhtien.cineasy.dto.response.UserResponse;
import com.quynhtien.cineasy.entity.Role;
import com.quynhtien.cineasy.entity.User;
import com.quynhtien.cineasy.enums.RoleEnum;
import com.quynhtien.cineasy.exception.AppException;
import com.quynhtien.cineasy.exception.ErrorCode;
import com.quynhtien.cineasy.mapper.UserMapper;
import com.quynhtien.cineasy.repository.RoleRepository;
import com.quynhtien.cineasy.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;
    AuthenticationService authenticationService;

    //Get all users
    public List<UserResponse> getUsers() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("Get users request by: {}", auth.getAuthorities());

        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse).toList();
    }

    //Get user by id
    public UserResponse getUser(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toUserResponse(user);
    }

    //Get my Info
    public UserResponse getMyInfo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        log.info("Get my info request by: {}", username);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toUserResponse(user);
    }

    //Create user
    public AuthenticationResponse createUser(UserCreationRequest request) {
        User user = userMapper.toUser(request);

        //encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //manually map roles
        List<Role> roles;
        if(request.getRoles() == null || request.getRoles().isEmpty()) {
            //assign default role USER
            Role userRole = roleRepository.findById(RoleEnum.USER.name())
                    .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));
            roles = List.of(userRole);
        } else {
            roles = roleRepository.findAllById(request.getRoles());
        }
        user.setRoles(roles);

        userRepository.save(user);
        return authenticationService.login(
                new AuthenticationRequest(request.getUsername(), request.getPassword()));
    }

    //Update user
    public UserResponse updateUser(String id, UserUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        //encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //manually map roles
        List<Role> roles = roleRepository.findAllById(request.getRoles());
        user.setRoles(roles);

        userMapper.updateUser(request, user);
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    //Delete user
    public String deleteUser(String id) {
        if (!userRepository.existsById(id)) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }

        userRepository.deleteById(id);
        return "Delete user successfully";
    }
}
