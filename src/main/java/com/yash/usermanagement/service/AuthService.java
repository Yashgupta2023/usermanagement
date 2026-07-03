package com.yash.usermanagement.service;

import com.yash.usermanagement.enums.Role;
import com.yash.usermanagement.dto.CreateUserRequest;
import com.yash.usermanagement.dto.LoginRequest;
import com.yash.usermanagement.dto.LoginResponse;
import com.yash.usermanagement.dto.UserDTO;
import com.yash.usermanagement.entity.User;
import com.yash.usermanagement.exception.DuplicateResourceException;
import com.yash.usermanagement.mapper.MapperUtil;
import com.yash.usermanagement.repository.UserRepository;
import com.yash.usermanagement.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    public UserDTO register(CreateUserRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new DuplicateResourceException(
                    "Email already registered."
            );
        }

        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        // Encrypt password
        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        // Default role
        user.setRole(Role.ROLE_USER);

        User savedUser = userRepository.save(user);

        return MapperUtil.mapToUserDTO(savedUser);
    }

    public LoginResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserDetails userDetails =
                customUserDetailsService.loadUserByUsername(request.getEmail());

        String token = jwtUtil.generateToken(userDetails);

        return new LoginResponse(token);
    }
}