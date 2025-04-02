package com.prodmanager.core.auth;

import com.prodmanager.core.user.dto.UserSignupRequestDto;
import com.prodmanager.core.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/auth")
public class SignUpController {

    private UserService userService;

    @Autowired
    public SignUpController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    @RequestMapping("signup")
    public ResponseEntity<String> signUp(@RequestBody UserSignupRequestDto signUpDto) {
        // Validate and create a new user account
        // This is where you would typically call a service to handle the registration logic
        this.userService.registerUser(signUpDto);

        // For this example, we'll assume registration is successful
        return ResponseEntity.ok("User registered successfully with username: " + signUpDto.getUserName());
    }
}