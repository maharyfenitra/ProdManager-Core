package com.prodmanager.core.user.service;

import com.prodmanager.core.exception.ConflictException;
import com.prodmanager.core.exception.EntityNotFoundException;
import com.prodmanager.core.user.dto.UserSignupRequestDto;
import com.prodmanager.core.user.dto.UserSignupResponseDto;
import com.prodmanager.core.user.entity.UserEntity;
import com.prodmanager.core.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;

    public void saveUser(UserSignupRequestDto userDto) {
        // Fetch the existing user
        Optional<UserEntity> existingUserOpt = userRepository.findByUserName(userDto.getUserName());
        if (existingUserOpt.isPresent()) {
            throw new ConflictException("User already exist");
        }
        // Encode the password
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        // Convert UserDTO to UserEntity using ModelMapper
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userEntity.setPassword(encodedPassword);
        System.out.println(userEntity.getUserName());
        UserEntity userDetails = userRepository.save(userEntity);
    }

    public UserSignupResponseDto registerUser(UserSignupRequestDto dto) {
        UserEntity user = UserEntity.builder()
                .userName(dto.getUserName())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
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
    public List<UserSignupResponseDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserSignupResponseDto.class))
                .toList();
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
