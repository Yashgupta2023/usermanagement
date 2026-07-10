package com.yash.usermanagement.service;

import com.yash.usermanagement.dto.CreateUserRequest;
import com.yash.usermanagement.dto.UpdateUserRequest;
import com.yash.usermanagement.dto.UserDTO;
import com.yash.usermanagement.entity.User;
import com.yash.usermanagement.exception.DuplicateResourceException;
import com.yash.usermanagement.exception.ResourceNotFoundException;
import com.yash.usermanagement.mapper.MapperUtil;
import com.yash.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileStorageService fileStorageService;

    public List<UserDTO> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(MapperUtil::mapToUserDTO)
                .collect(Collectors.toList());
    }

    public UserDTO createUser(CreateUserRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new DuplicateResourceException(
                    "User already exists with email: " + request.getEmail()
            );
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        User savedUser = userRepository.save(user);

        return MapperUtil.mapToUserDTO(savedUser);
    }

    public UserDTO getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + id
                        ));

        return MapperUtil.mapToUserDTO(user);
    }

    public UserDTO getUserByEmail(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with email: " + email
                        ));

        return MapperUtil.mapToUserDTO(user);
    }

    public UserDTO updateUser(Long id, UpdateUserRequest request) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + id
                        ));

        existingUser.setUsername(request.getUsername());
        existingUser.setEmail(request.getEmail());

        User updatedUser = userRepository.save(existingUser);

        return MapperUtil.mapToUserDTO(updatedUser);
    }

    public boolean deleteUser(Long id) {

        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "User not found with id: " + id
            );
        }

        userRepository.deleteById(id);
        return true;
    }

    // ==============================
    // Upload Profile Picture
    // ==============================

    public String uploadProfilePicture(Long userId,
                                       MultipartFile file)
            throws IOException {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + userId
                        ));

        String fileName = fileStorageService.uploadFile(file);

        user.setProfilePicture(fileName);

        userRepository.save(user);

        return fileName;
    }
}