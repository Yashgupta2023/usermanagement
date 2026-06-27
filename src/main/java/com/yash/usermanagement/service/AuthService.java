package com.yash.usermanagement.service;

import com.yash.usermanagement.dto.CreateUserRequest;
import com.yash.usermanagement.dto.UserDTO;
import com.yash.usermanagement.entity.User;
import com.yash.usermanagement.exception.DuplicateResourceException;
import com.yash.usermanagement.mapper.MapperUtil;
import com.yash.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO register(CreateUserRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new DuplicateResourceException(
                    "Email already registered."
            );
        }

        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        // Encrypt password before saving
        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        User savedUser = userRepository.save(user);

        return MapperUtil.mapToUserDTO(savedUser);
    }
}