package com.saraconference.backend.service;


import com.saraconference.backend.entity.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    void deleteUser(Long id);

    long getTotalPapers(String email);

    long getTotalSubmittedPapers(Long userId);

    long getTotalUnderReviewPapers(Long userId);

    long getTotalAcceptedPapers(Long userId);
}
