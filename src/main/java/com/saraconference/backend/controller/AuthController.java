package com.saraconference.backend.controller;

import com.saraconference.backend.Exception.BadRequestException;
import com.saraconference.backend.dto.AuthRequest;
import com.saraconference.backend.dto.AuthResponse;
import com.saraconference.backend.dto.LoginRequest;
import com.saraconference.backend.dto.LoginResponse;
import com.saraconference.backend.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody AuthRequest request) {
       if(request.getEmail() == null || request.getEmail().isBlank()){
           logger.warn("Login attempt with missing email");
           throw new BadRequestException("Email is required");
       }

         if(request.getPassword() == null || request.getPassword().isBlank()){
              logger.warn("Login attempt with missing password for email: {}", request.getEmail());
              throw new BadRequestException("Password is required");
         }
         logger.debug("The role is {}", request.getRole());
          LoginResponse response = authService.login(request.getEmail(), request.getPassword(), request.getRole());
          return ResponseEntity.ok(response);
    }
}
