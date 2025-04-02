package com.prodmanager.core.auth;


import com.prodmanager.core.exception.AuthenticationException;
import com.prodmanager.core.user.dto.UserLoginResponseDto;
import com.prodmanager.core.user.entity.UserEntity;
import com.prodmanager.core.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserLoginResponseDto authenticate(String userName, String password) {
        Optional<UserEntity> userEntity = userRepository.findByUserName(userName);
        if(userEntity.isPresent()){
            if(userEntity.get().getPassword().equals(password) ){
                return new UserLoginResponseDto("token");
            }
        }
        throw new AuthenticationException("Login incorrect");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByUserName(username);
        if(!user.isPresent())
            throw new UsernameNotFoundException("Can't find user");
        UserEntity userEntity = user.get();

        return User.builder()
                .username(userEntity.getUserName())
                .password(userEntity.getPassword())
                .build();
    }
}
