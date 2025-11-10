package com.saraconference.backend.service;

import com.saraconference.backend.dto.PaperSubmissionResponse;
import com.saraconference.backend.entity.PaperSubmission;
import com.saraconference.backend.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PaperSubmissionService {
    /**
     * Submit a new paper
     */
    PaperSubmissionResponse submitPaper(String name, String email, String contactNo,
                                        String department, String collegeName,
                                        String paperTitle, String paperAbstract,
                                        MultipartFile paperFile) throws Exception;

    /**
     * Get all papers
     */
    List<PaperSubmissionResponse> getAllPapers();

    /**
     * Get paper by ID
     */
    PaperSubmissionResponse getPaperById(Long id);

    /**
     * Get papers by department
     */
    List<PaperSubmissionResponse> getPapersByDepartment(String department);

    /**
     * Search papers by title
     */
    List<PaperSubmissionResponse> searchPapersByTitle(String query);

    /**
     * Download paper file
     */
    byte[] downloadPaper(Long id);

    /**
     * Delete paper
     */
    void deletePaper(Long id);

    /**
     * Get papers by email
     */
    List<PaperSubmissionResponse> getPapersByEmail(String email);

    List<PaperSubmission> findByEvaluator(User evaluator);

    void assignEvaluatorToPaper(Long paperId, Long evaluatorId);

    PaperSubmissionResponse updatePaperStatus(Long paperId, String status);

    List<PaperSubmissionResponse> getPapersByEvaluator(User evaluator);

    List<PaperSubmissionResponse> getPapersByEvaluatorUsername(String evaluatorUsername);
}

