package com.yash.usermanagement.service;

import com.yash.usermanagement.dto.CreateUserRequest;
import com.yash.usermanagement.dto.UpdateUserRequest;
import com.yash.usermanagement.dto.UserDTO;
import com.yash.usermanagement.entity.User;
import com.yash.usermanagement.exception.DuplicateResourceException;
import com.yash.usermanagement.exception.ResourceNotFoundException;
import com.yash.usermanagement.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testCreateUser() {

        CreateUserRequest request = new CreateUserRequest();
        request.setUsername("Yash");
        request.setEmail("yash@gmail.com");

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setUsername("Yash");
        savedUser.setEmail("yash@gmail.com");

        when(userRepository.findByEmail(request.getEmail()))
                .thenReturn(Optional.empty());

        when(userRepository.save(any(User.class)))
                .thenReturn(savedUser);

        UserDTO result = userService.createUser(request);

        assertEquals("Yash", result.getUsername());
        assertEquals("yash@gmail.com", result.getEmail());

        verify(userRepository).save(any(User.class));
    }

    @Test
    void testGetUserById() {

        User user = new User();
        user.setId(1L);
        user.setUsername("Yash");
        user.setEmail("yash@gmail.com");

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        UserDTO result = userService.getUserById(1L);

        assertEquals(1L, result.getId());
        assertEquals("Yash", result.getUsername());
        assertEquals("yash@gmail.com", result.getEmail());

        verify(userRepository).findById(1L);
    }

    @Test
    void testGetAllUsers() {

        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("Yash");
        user1.setEmail("yash@gmail.com");

        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("Rahul");
        user2.setEmail("rahul@gmail.com");

        when(userRepository.findAll())
                .thenReturn(List.of(user1, user2));

        List<UserDTO> users = userService.getAllUsers();

        assertEquals(2, users.size());
        assertEquals("Yash", users.get(0).getUsername());
        assertEquals("Rahul", users.get(1).getUsername());

        verify(userRepository).findAll();
    }

    @Test
    void testUpdateUser() {

        UpdateUserRequest request = new UpdateUserRequest();
        request.setUsername("Updated");
        request.setEmail("updated@gmail.com");

        User user = new User();
        user.setId(1L);
        user.setUsername("Old");
        user.setEmail("old@gmail.com");

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        when(userRepository.save(any(User.class)))
                .thenReturn(user);

        UserDTO result = userService.updateUser(1L, request);

        assertEquals("Updated", result.getUsername());
        assertEquals("updated@gmail.com", result.getEmail());

        verify(userRepository).save(any(User.class));
    }

    @Test
    void testDeleteUser() {

        when(userRepository.existsById(1L))
                .thenReturn(true);

        boolean deleted = userService.deleteUser(1L);

        assertTrue(deleted);

        verify(userRepository).deleteById(1L);
    }

    @Test
    void testCreateUserDuplicateEmail() {

        CreateUserRequest request = new CreateUserRequest();
        request.setUsername("Yash");
        request.setEmail("yash@gmail.com");

        User user = new User();
        user.setEmail("yash@gmail.com");

        when(userRepository.findByEmail(request.getEmail()))
                .thenReturn(Optional.of(user));

        assertThrows(
                DuplicateResourceException.class,
                () -> userService.createUser(request)
        );

        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testGetUserByIdNotFound() {

        when(userRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> userService.getUserById(1L)
        );

        verify(userRepository).findById(1L);
    }
}