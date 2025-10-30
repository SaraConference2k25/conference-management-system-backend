package com.saraconference.backend.service.impl;

import com.saraconference.backend.dto.AuthRequest;
import com.saraconference.backend.dto.AuthResponse;
import com.saraconference.backend.dto.LoginResponse;
import com.saraconference.backend.entity.Role;
import com.saraconference.backend.entity.User;
import com.saraconference.backend.repository.RoleRepository;
import com.saraconference.backend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.saraconference.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements AuthService {
    private Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
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
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.getRoles().add(role);
        logger.info("Assigned role: {} to user: {}", role.getRoleName(), request.getUsername());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        logger.info("Registering user: {} with email: {} and role: {}", request.getUsername(), request.getEmail(), request.getRole());
        userRepository.save(user);

        return new AuthResponse("User registered successfully!", true);
    }
    @Override
    public LoginResponse login(String email, String password, String requestedRole) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        boolean hasRole = user.getRoles().stream()
                .anyMatch(role -> role.getRoleName().equalsIgnoreCase(requestedRole));

        if (!hasRole) {
            throw new RuntimeException("Access Denied for role: " + requestedRole);
        }

        logger.info("User {} logged in with role {}", user.getUsername(), requestedRole);
        return new LoginResponse("Login successful", user.getEmail(), requestedRole,user.getUsername(), HttpStatus.OK.value());
    }



}
