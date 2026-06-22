package com.saraconference.backend.controller;

import com.saraconference.backend.entity.User;
import com.saraconference.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @GetMapping("/total-papers")
    public ResponseEntity<?> getTotalPapers(@RequestParam("email") String email) {
        try {
            long totalPapers = userService.getTotalPapers(email);
            return ResponseEntity.ok(totalPapers);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error retrieving total submitted papers: " + e.getMessage());
        }
    }

    @GetMapping("/net-submitted-papers")
    public ResponseEntity<?> getNetSubmittedPapers(@RequestParam("userId") Long userId) {
        try {
                 // Assuming userId is the authenticated user's ID
            long netSubmittedPapers = userService.getTotalSubmittedPapers(userId);
            return ResponseEntity.ok(netSubmittedPapers);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error retrieving net submitted papers: " + e.getMessage());
        }
    }

    @GetMapping("/net-underreview-papers")
    public ResponseEntity<?> getNetUnderReviewPapers(@RequestParam("userId") Long userId) {
        try {
            long netSubmittedPapers = userService.getTotalUnderReviewPapers(userId);
            return ResponseEntity.ok(netSubmittedPapers);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error retrieving net submitted papers: " + e.getMessage());
        }
    }

    @GetMapping("/net-accepted-papers")
    public ResponseEntity<?> getNetAcceptedPapersByUser(@RequestParam("userId") Long userId) {
        try {
            long netSubmittedPapers = userService.getTotalAcceptedPapers(userId);
            return ResponseEntity.ok(netSubmittedPapers);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error retrieving net submitted papers: " + e.getMessage());
        }
    }
}

