package com.saraconference.backend.repository;

import com.saraconference.backend.entity.PaperSubmission;
import com.saraconference.backend.entity.User;
import com.saraconference.backend.enums.PaperStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaperSubmissionRepository extends JpaRepository<PaperSubmission, Long> {
    List<PaperSubmission> findByDepartment(String department);
    List<PaperSubmission> findByPaperTitleContainingIgnoreCase(String title);
    List<PaperSubmission> findByEmail(String email);
    List<PaperSubmission> findByEvaluator(User evaluator);
    boolean existsByPaperId(String paperId);
    Optional<PaperSubmission> findByPaperId(String paperId);

    long countByStatus(PaperStatus paperStatus);
}
