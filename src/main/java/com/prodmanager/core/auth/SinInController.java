package com.prodmanager.core.auth;

import com.prodmanager.core.config.util.JwtTokenUtil;
import com.prodmanager.core.user.dto.UserLoginRequestDto;
import com.prodmanager.core.user.dto.UserLoginResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/login")
public class SinInController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody UserLoginRequestDto loginDto) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword())
            );

            UserDetails userDetails = authService.loadUserByUsername(loginDto.getUserName());
            String jwt = jwtTokenUtil.generateToken(userDetails);
            // If authentication is successful, return a response

            return ResponseEntity.ok(new UserLoginResponseDto(jwt));
        } catch (AuthenticationException e) {
            // If authentication fails, return an error response
            return ResponseEntity.status(401).body(new UserLoginResponseDto("Error login"));
        }

    }
}
