package com.saraconference.backend.service;

import com.saraconference.backend.dto.CreateEvaluatorRequest;
import com.saraconference.backend.dto.EvaluatorResponse;
import com.saraconference.backend.dto.PaperSubmissionResponse;

import java.util.List;

public interface EvaluatorService {
    EvaluatorResponse createEvaluator(CreateEvaluatorRequest request);
    List<EvaluatorResponse> getAllEvaluators();
    EvaluatorResponse getEvaluatorById(Long id);
    EvaluatorResponse updateEvaluator(Long id, CreateEvaluatorRequest request);
    void deleteEvaluator(Long id);
    long getTotalEvaluators();
    long getActiveEvaluators();
    long getAvailableEvaluators();
    void assignEvaluatorToPaper(Long evaluatorId, Long paperId);
    List<PaperSubmissionResponse> getPapersByEvaluatorUsername(String evaluatorUsername);

    List<PaperSubmissionResponse> getPapersAssignedToEvaluator(Long evaluatorId);
}
