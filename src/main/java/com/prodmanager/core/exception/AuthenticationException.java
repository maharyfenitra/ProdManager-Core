package com.prodmanager.core.exception;


public class AuthenticationException extends RuntimeException{
    public AuthenticationException(String message){
        super(message);
    }
}
