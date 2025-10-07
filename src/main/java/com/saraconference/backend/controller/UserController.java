package com.saraconference.backend.controller;


import com.saraconference.backend.entity.User;
import com.saraconference.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){
         userService.register(user);
         return ResponseEntity.ok(user);
    }
}
