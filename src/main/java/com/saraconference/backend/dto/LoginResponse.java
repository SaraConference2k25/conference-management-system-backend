package com.saraconference.backend.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String email;
    private String role;
    private String message;
    public  LoginResponse() {}
    public LoginResponse(String message) {
        this.message = message;
    }


    public LoginResponse(String loginSuccessful, String username) {
        this.message = loginSuccessful + " Welcome, " + username + "!";

    }
    public LoginResponse(String message, String email, String role) {  // ADD THIS
        this.message = message;
        this.email = email;
        this.role = role;
    }

}
