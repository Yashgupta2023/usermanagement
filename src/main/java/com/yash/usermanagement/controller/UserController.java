package com.yash.usermanagement.controller;

import com.yash.usermanagement.dto.CreateUserRequest;
import com.yash.usermanagement.dto.UpdateUserRequest;
import com.yash.usermanagement.dto.UserDTO;
import com.yash.usermanagement.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "User Management APIs")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Get all users")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(summary = "Get user by ID")
    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @Operation(summary = "Create a user")
    @PostMapping
    public UserDTO createUser(
            @Valid @RequestBody CreateUserRequest request) {

        return userService.createUser(request);
    }

    @Operation(summary = "Get user by email")
    @GetMapping("/email/{email}")
    public UserDTO getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @Operation(summary = "Update a user")
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequest request) {

        UserDTO updatedUser =
                userService.updateUser(id, request);

        return ResponseEntity.ok(updatedUser);
    }

    @Operation(summary = "Delete a user")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(
            @PathVariable Long id) {

        userService.deleteUser(id);

        return ResponseEntity.ok(
                "User deleted successfully"
        );
    }

    @Operation(summary = "Upload profile picture")
    @PostMapping("/{id}/upload")
    public ResponseEntity<Map<String, String>> uploadProfilePicture(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file)
            throws IOException {

        String fileName = userService.uploadProfilePicture(id, file);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Profile picture uploaded successfully");
        response.put("fileName", fileName);

        return ResponseEntity.ok(response);
    }
}