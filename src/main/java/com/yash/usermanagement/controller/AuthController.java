package com.yash.usermanagement.controller;

import com.yash.usermanagement.dto.CreateUserRequest;
import com.yash.usermanagement.dto.LoginRequest;
import com.yash.usermanagement.dto.LoginResponse;
import com.yash.usermanagement.dto.UserDTO;
import com.yash.usermanagement.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Authentication APIs")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Operation(summary = "Register a new user")
    @PostMapping("/register")
    public UserDTO register(
            @Valid @RequestBody CreateUserRequest request) {

        return authService.register(request);
    }

    @Operation(summary = "Login user and generate JWT")
    @PostMapping("/login")
    public LoginResponse login(
            @Valid @RequestBody LoginRequest request) {

        return authService.login(request);
    }
}