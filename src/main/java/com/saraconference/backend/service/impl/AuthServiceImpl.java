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
    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            return new LoginResponse("Invalid credentials", null, null);
        }

        // Extract the role name from user's roles
        String roleName = user.getRoles().stream()
                .findFirst()
                .map(Role::getRoleName)
                .orElse("PARTICIPANT");

        return new LoginResponse("Login successful", user.getEmail(), roleName);
    }

}
