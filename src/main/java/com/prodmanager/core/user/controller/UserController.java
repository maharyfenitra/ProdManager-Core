package com.prodmanager.core.user.controller;

import com.prodmanager.core.user.dto.UserRequestDto;
import com.prodmanager.core.user.dto.UserResponseDto;
import com.prodmanager.core.user.dto.UserSignupRequestDto;
import com.prodmanager.core.user.dto.UserSignupResponseDto;
import com.prodmanager.core.user.entity.UserEntity;
import com.prodmanager.core.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/users")
public class UserController {

    @Autowired
    private UserService userService;

    // CREATE
    @PostMapping
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody UserRequestDto userDto) {
        UserResponseDto createdUser = userService.saveUser(userDto);
        return ResponseEntity.ok(createdUser);
    }

    // READ - Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserSignupResponseDto> getUserById(@PathVariable Long id) {
        UserSignupResponseDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    // READ - Get all users
    @GetMapping
    public ResponseEntity<Page<UserSignupResponseDto>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<UserSignupResponseDto> users = userService.getAllUsers(
                PageRequest.of(page, size)
        );
        return ResponseEntity.ok(users);
    }

    // READ - Get user by username
    @GetMapping("/username/{username}")
    public ResponseEntity<UserEntity> getUserByUsername(@PathVariable String username) {
        UserEntity user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<UserSignupResponseDto> updateUser(@PathVariable Long id, @RequestBody UserSignupRequestDto dto) {
        UserSignupResponseDto updatedUser = userService.updateUser(id, dto);
        return ResponseEntity.ok(updatedUser);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
