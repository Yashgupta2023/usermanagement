package com.yash.usermanagement.service;

import com.yash.usermanagement.entity.User;
import com.yash.usermanagement.exception.DuplicateResourceException;
import com.yash.usermanagement.exception.ResourceNotFoundException;
import com.yash.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new DuplicateResourceException(
                    "User already exists with email: " + user.getEmail()
            );
        }

        return userRepository.save(user);
    }

    public User getUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + id
                        ));
    }

    public User getUserByEmail(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with email: " + email
                        ));
    }

    public User updateUser(Long id, User updatedUser) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + id
                        ));

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());

        return userRepository.save(existingUser);
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
}