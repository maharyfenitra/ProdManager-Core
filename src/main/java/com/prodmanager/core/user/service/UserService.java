package com.prodmanager.core.user.service;

import com.prodmanager.core.exception.EntityNotFoundException;
import com.prodmanager.core.user.dto.UserRequestDto;
import com.prodmanager.core.user.dto.UserResponseDto;
import com.prodmanager.core.user.dto.UserSignupRequestDto;
import com.prodmanager.core.user.dto.UserSignupResponseDto;
import com.prodmanager.core.user.entity.UserEntity;
import com.prodmanager.core.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;

    public UserResponseDto saveUser(UserRequestDto dto) {
        UserEntity user = UserEntity.builder()
                .userName(dto.getUserName())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .role(dto.getRole())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))  // Hachage du mot de passe
                .phoneNumber(dto.getPhoneNumber())
                .build();

        return modelMapper.map(user, UserResponseDto.class);
    }

    public UserSignupResponseDto registerUser(UserSignupRequestDto dto) {
        UserEntity user = UserEntity.builder()
                .userName(dto.getUserName())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .role(0)
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))  // Hachage du mot de passe
                .phoneNumber(dto.getPhoneNumber())
                .build();

        return modelMapper.map(user, UserSignupResponseDto.class);

    }

    // READ - Get one user by ID
    public UserSignupResponseDto getUserById(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        return modelMapper.map(user, UserSignupResponseDto.class);
    }

    // READ - Get all users
    public Page<UserSignupResponseDto> getAllUsers(Pageable pageable) {
        Page<UserEntity> users = userRepository.findAll(pageable);

        return  users.map(
                user -> modelMapper.map(user, UserSignupResponseDto.class));
    }

    // READ - Get user by username
    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUserName(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found with username: " + username));
    }

    // UPDATE - Update user details
    public UserSignupResponseDto updateUser(Long id, UserSignupRequestDto dto) {

        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setDescription(dto.getDescription());

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        UserEntity updated = userRepository.save(user);
        return modelMapper.map(updated, UserSignupResponseDto.class);
    }


    // DELETE - Delete user by ID
    public void deleteUser(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        userRepository.delete(user);
    }

}
