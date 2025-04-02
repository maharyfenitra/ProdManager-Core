package com.prodmanager.core.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserLoginRequestDto {

    private String userName;
    private String password;

    // Constructors, getters, and setters

    public UserLoginRequestDto() {
    }

    public UserLoginRequestDto(@JsonProperty("userName") String userName, @JsonProperty("password") String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

