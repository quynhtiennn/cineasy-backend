package com.quynhtien.cineasy.controller;

import com.quynhtien.cineasy.entity.User;
import com.quynhtien.cineasy.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    UserService userService;

    //Get all users
    @GetMapping
    public List<User> findAll() {
        return userService.getUsers();
    }

    //Get user by id
    @GetMapping("/{id}")
    public User findUserById(@PathVariable String id) {
        return userService.getUser(id);
    }

    //Create user
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    //Update user
    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User user) {
        return userService.updateUser(user);
    }

    //Delete user
    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable String id, @RequestBody User user) {
        userService.deleteUser(id);
        return null;
    }

}
