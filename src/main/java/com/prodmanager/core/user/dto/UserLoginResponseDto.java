package com.prodmanager.core.user.dto;

public class UserLoginResponseDto {
    private String token;

    public UserLoginResponseDto(String token){
        this.token= token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

