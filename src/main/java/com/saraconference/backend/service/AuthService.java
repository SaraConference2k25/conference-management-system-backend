package com.saraconference.backend.service;


import com.saraconference.backend.dto.AuthRequest;
import com.saraconference.backend.dto.AuthResponse;
import com.saraconference.backend.dto.LoginResponse;

public interface AuthService {
    AuthResponse register(AuthRequest request);
    LoginResponse login(String email, String password, String role);
}
