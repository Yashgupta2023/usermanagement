package com.yash.usermanagement.controller;

import com.yash.usermanagement.dto.CreateUserRequest;
import com.yash.usermanagement.dto.UserDTO;
import com.yash.usermanagement.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.yash.usermanagement.dto.LoginRequest;
import com.yash.usermanagement.dto.LoginResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public UserDTO register(
            @Valid @RequestBody CreateUserRequest request) {

        return authService.register(request);
    }
    @PostMapping("/login")
    public LoginResponse login(
            @Valid @RequestBody LoginRequest request) {

        return authService.login(request);
    }
}