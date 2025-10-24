package com.saraconference.backend.dto;

import lombok.*;

@Getter @Setter
public class AuthRequest {
    private String email;
    private String password;
    private String role;
    public AuthRequest() {
            
    }
}
