package com.saraconference.backend.repository;

import com.saraconference.backend.entity.PaperSubmission;
import com.saraconference.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface PaperSubmissionRepository extends JpaRepository<PaperSubmission, Long> {
    List<PaperSubmission> findByDepartment(String department);
    List<PaperSubmission> findByPaperTitleContainingIgnoreCase(String title);
    List<PaperSubmission> findByEmail(String email);
    List<PaperSubmission> findByEvaluator(User evaluator);

}
