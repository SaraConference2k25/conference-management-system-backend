package com.saraconference.backend.service.impl;


import com.saraconference.backend.entity.User;
import com.saraconference.backend.enums.PaperStatus;
import com.saraconference.backend.repository.PaperSubmissionRepository;
import com.saraconference.backend.repository.UserRepository;
import com.saraconference.backend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PaperSubmissionRepository paperSubmissionRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public long getTotalPapers(String email) {
        return paperSubmissionRepository.countByEmail(email);
    }

    @Override
    public long getTotalSubmittedPapers(String email) {
        long totalPendingAssignmentPapers = paperSubmissionRepository.countByEmailAndStatus(email,PaperStatus.PENDING_ASSIGNMENT);
        long totalUnderReviewPapers = paperSubmissionRepository.countByEmailAndStatus(email, PaperStatus.UNDER_REVIEW);
        long totalSubmittedPapers = totalPendingAssignmentPapers + totalUnderReviewPapers;
        return totalSubmittedPapers;
    }

    @Override
    public long getTotalUnderReviewPapers(String email) {
        long totalUnderReviewPapers = paperSubmissionRepository.countByEmailAndStatus(email, PaperStatus.UNDER_REVIEW);
        return totalUnderReviewPapers;
    }

    @Override
    public long getTotalAcceptedPapers(String email) {
        long totalAcceptedPapers = paperSubmissionRepository.countByEmailAndStatus(email, PaperStatus.ACCEPTED);
        return totalAcceptedPapers;
    }
}
