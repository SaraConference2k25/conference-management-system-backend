package com.saraconference.backend.service.impl;

import com.saraconference.backend.dto.AuthRequest;
import com.saraconference.backend.dto.AuthResponse;
import com.saraconference.backend.dto.LoginRequest;
import com.saraconference.backend.dto.LoginResponse;
import com.saraconference.backend.entity.Role;
import com.saraconference.backend.entity.User;
import com.saraconference.backend.repository.RoleRepository;
import com.saraconference.backend.repository.UserRepository;

import com.saraconference.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse register(AuthRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return new AuthResponse("Email already in use");
        }

        Role role = roleRepository.findByRoleName(request.getRole())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = new User();
        user.setUsername(request.getEmail());
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.getRoles().add(role);

        userRepository.save(user);

        return new AuthResponse("User registered successfully!", true);
    }
    public LoginResponse login(String email, String password, String requestedRole) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new RuntimeException("Invalid credentials");
        }

        // Check if user has the requested role
        boolean hasRole = user.getRoles().stream()
                .anyMatch(role -> role.getRoleName().equals(requestedRole));

        if (!hasRole) {
            throw new  RuntimeException("Access Denied for " + requestedRole + " role : " + HttpStatus.UNAUTHORIZED);
        }

        return new LoginResponse("Login successful", user.getEmail(), requestedRole);
    }


}
