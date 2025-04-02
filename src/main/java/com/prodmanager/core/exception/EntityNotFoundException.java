package com.prodmanager.core.exception;

public class EntityNotFoundException extends RuntimeException {

    // Constructeur avec message personnalisé
    public EntityNotFoundException(String message) {
        super(message);
    }

    // Constructeur avec message personnalisé et cause
    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
