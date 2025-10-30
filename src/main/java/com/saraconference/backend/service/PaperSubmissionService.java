package com.saraconference.backend.service;

import com.saraconference.backend.dto.PaperSubmissionRequest;
import com.saraconference.backend.dto.PaperSubmissionResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PaperSubmissionService {
    PaperSubmissionResponse submitPaper(String userEmail, PaperSubmissionRequest request, MultipartFile file);
    PaperSubmissionResponse getPaperById(Long id, String userEmail);
    List<PaperSubmissionResponse> getUserPapers(String userEmail);
    List<PaperSubmissionResponse> getAllPapers();
    List<PaperSubmissionResponse> getPapersByStatus(String status);
    List<PaperSubmissionResponse> getPapersByDepartment(String department);
    PaperSubmissionResponse reviewPaper(Long id, String reviewerEmail, String status, String comments);
    void deletePaper(Long id, String userEmail);
    byte[] downloadPaper(Long id, String userEmail);
}
