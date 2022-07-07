package com.softserveinc.controller;

import com.softserveinc.model.user.Role;
import com.softserveinc.model.user.dto.UserRequest;
import com.softserveinc.model.user.dto.UserResponse;
import com.softserveinc.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody UserRequest request) {
        return userService.createUser(request)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "User with given username already exist!"));
    }

    @GetMapping("/role")
    public Role getRole(String username) {
        return userService.getRoleOfLoggedUser(username);
    }
}
