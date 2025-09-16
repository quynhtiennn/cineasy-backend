package com.quynhtien.cineasy.service;

import com.quynhtien.cineasy.entity.User;
import com.quynhtien.cineasy.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class UserService {
    UserRepository userRepository;

    //Get all users
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    //Get user by id
    public User getUser(String id) {
        return userRepository.findById(id).orElse(null);
    }

    //Create user
    public User createUser(User user) {
        return userRepository.save(user);
    }

    //Update user
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    //Delete user
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
