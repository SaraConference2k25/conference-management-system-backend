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
    public long getTotalPapers(long userId) {
        return paperSubmissionRepository.countByUser(userId);
    }

    @Override
    public long getTotalSubmittedPapers(Long userId) {
        long totalPendingAssignmentPapers = paperSubmissionRepository.countByStatus(PaperStatus.PENDING_ASSIGNMENT);
        long totalUnderReviewPapers = paperSubmissionRepository.countByStatus(PaperStatus.UNDER_REVIEW);
        long totalSubmittedPapers = totalPendingAssignmentPapers + totalUnderReviewPapers;
        return totalSubmittedPapers;
    }

    @Override
    public long getTotalUnderReviewPapers(Long userId) {
        long totalUnderReviewPapers = paperSubmissionRepository.countByStatus(PaperStatus.UNDER_REVIEW);
        return totalUnderReviewPapers;
    }

    @Override
    public long getTotalAcceptedPapers(Long userId) {
        long totalAcceptedPapers = paperSubmissionRepository.countByStatus(PaperStatus.ACCEPTED);
        return totalAcceptedPapers;
    }
}
