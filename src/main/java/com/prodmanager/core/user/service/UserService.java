package com.prodmanager.core.user.service;

import com.prodmanager.core.exception.ConflictException;
import com.prodmanager.core.user.dto.UserSignupRequestDto;
import com.prodmanager.core.user.entity.UserEntity;
import com.prodmanager.core.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public UserEntity registerUser(UserSignupRequestDto dto) {
        UserEntity user = UserEntity.builder()
                .userName(dto.getUserName())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))  // Hachage du mot de passe
                .phoneNumber(dto.getPhoneNumber())
                .build();

        return userRepository.save(user);
    }

}
