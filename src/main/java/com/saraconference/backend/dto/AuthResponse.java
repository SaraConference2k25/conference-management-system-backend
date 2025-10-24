package com.saraconference.backend.dto;

public class AuthResponse {
    private String message;

    public AuthResponse(String message) {
        this.message = message;
    }

    public AuthResponse(String loginSuccessful, String username) {
        this.message = loginSuccessful + " Welcome, " + username + "!";
    }

    public AuthResponse(String emailAlreadyInUse, boolean b) {
        this.message = emailAlreadyInUse;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
