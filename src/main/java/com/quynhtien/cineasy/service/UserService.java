package com.quynhtien.cineasy.service;

import com.quynhtien.cineasy.dto.request.UserCreationRequest;
import com.quynhtien.cineasy.dto.request.UserUpdateRequest;
import com.quynhtien.cineasy.dto.response.UserResponse;
import com.quynhtien.cineasy.entity.User;
import com.quynhtien.cineasy.mapper.UserMapper;
import com.quynhtien.cineasy.repository.UserRepository;
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
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    //Get all users
    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse).toList();
    }

    //Get user by id
    public UserResponse getUser(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toUserResponse(user);
    }

    //Create user
    public UserResponse createUser(UserCreationRequest request) {
        User user = userMapper.toUser(request);
        log.info("request {}", request.getFirstName());
        log.info("user {}", user.getFirstName());
        //encode password
        //

        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    //Update user
    public UserResponse updateUser(String id, UserUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        //encode password

        userMapper.updateUser(request, user);
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    //Delete user
    public String deleteUser(String id) {
        userRepository.deleteById(id);
        return "Delete user successfully";
    }
}
