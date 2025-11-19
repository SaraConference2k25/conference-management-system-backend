package com.saraconference.backend.service.impl;

import com.saraconference.backend.dto.PaperSubmissionResponse;
import com.saraconference.backend.entity.PaperSubmission;
import com.saraconference.backend.entity.Role;
import com.saraconference.backend.entity.User;
import com.saraconference.backend.enums.PaperStatus;
import com.saraconference.backend.repository.PaperSubmissionRepository;
import com.saraconference.backend.repository.RoleRepository;
import com.saraconference.backend.repository.UserRepository;
import com.saraconference.backend.service.BlobStorageService;
import com.saraconference.backend.service.PaperSubmissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.saraconference.backend.util.PaperIdGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaperSubmissionServiceImpl implements PaperSubmissionService {

    private static final Logger logger = LoggerFactory.getLogger(PaperSubmissionServiceImpl.class);

    @Autowired
    private PaperSubmissionRepository paperSubmissionRepository;

    @Autowired
    private BlobStorageService blobStorageService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public PaperSubmissionResponse submitPaper(String name, String email, String contactNo,
                                               String department, String collegeName,
                                               String paperTitle, String paperAbstract,
                                               MultipartFile paperFile) throws Exception {
        try {
            // Upload file to blob storage
            String fileUrl = blobStorageService.uploadFile(paperFile);
            String fileName = paperFile.getOriginalFilename();

            // Create paper submission
            PaperSubmission paper = new PaperSubmission();
            paper.setPaperId(generateUniquePaperId());
            paper.setName(name);
            paper.setEmail(email);
            paper.setContactNo(contactNo);
            paper.setDepartment(department);
            paper.setCollegeName(collegeName);
            paper.setPaperTitle(paperTitle);
            paper.setPaperAbstract(paperAbstract);
            paper.setPaperFileName(fileName);
            paper.setStatus(PaperStatus.PENDING_ASSIGNMENT);
            paper.setPaperFileUrl(fileUrl);
            paper.setSubmittedAt(LocalDateTime.now());

            PaperSubmission savedPaper = paperSubmissionRepository.save(paper);
            logger.info("Paper submitted successfully with ID: {}", savedPaper.getId());
            return convertToResponse(savedPaper);
        } catch (Exception e) {
            logger.error("Error submitting paper: {}", e.getMessage());
            throw new Exception("Error submitting paper: " + e.getMessage());
        }
    }

    @Override
    public List<PaperSubmissionResponse> getAllPapers() {
        try {
            return paperSubmissionRepository.findAll()
                    .stream()
                    .map(this::convertToResponse)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error retrieving all papers: {}", e.getMessage());
            return List.of();
        }
    }
    public void assignEvaluatorToPaper(String paperId, Long evaluatorId) {
        PaperSubmission paper = paperSubmissionRepository.findByPaperId(paperId)
                .orElseThrow(() -> new RuntimeException("Paper not found"));
        logger.info("Assign evaluator to Paper ID: {}", paperId);
        User evaluator = userRepository.findById(evaluatorId)
                .orElseThrow(() -> new RuntimeException("Evaluator not found"));
        logger.info("Assign evaluator to User: {}", evaluator);
        // Verify user has evaluator role
        Role evaluatorRole = roleRepository.findByRoleName("EVALUATOR")
                .orElseThrow(() -> new RuntimeException("Evaluator role not found"));
        logger.info("Assign evaluator to Role: {}", evaluatorRole);
        if (!evaluator.getRoles().contains(evaluatorRole)) {
            throw new RuntimeException("User is not an evaluator");
        }
        Integer currentWorkload = evaluator.getWorkload() != null ? evaluator.getWorkload() : 0;
        evaluator.setWorkload(currentWorkload + 1);
        logger.info("Assigning paper to evaluator with ID: {}", paper.getPaperId());
        logger.info("Updated evaluator workload to: w{}", evaluator.getWorkload());
        paper.setEvaluator(evaluator);
        paper.setEvaluatorName(evaluator.getUsername());
        logger.info("Assigned paper to evaluator with ID: {}", paper.getId());
        logger.info("Assigning paper to evaluator with ID: {} and with name {}", paperId, evaluator.getUsername());
        paperSubmissionRepository.save(paper);
    }

    public List<PaperSubmissionResponse> getPapersAssignedToEvaluator(Long evaluatorId) {
        User evaluator = userRepository.findById(evaluatorId)
                .orElseThrow(() -> new RuntimeException("Evaluator not found"));

        return paperSubmissionRepository.findByEvaluator(evaluator).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }


    @Override
    public PaperSubmissionResponse getPaperById(String id) {
        try {
            PaperSubmission paper = paperSubmissionRepository.findByPaperId(id)
                    .orElseThrow(() -> new RuntimeException("Paper not found with ID: " + id));
            return convertToResponse(paper);
        } catch (Exception e) {
            logger.error("Error retrieving paper with ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Paper not found", e);
        }
    }

    @Override
    public List<PaperSubmissionResponse> getPapersByDepartment(String department) {
        try {
            return paperSubmissionRepository.findByDepartment(department)
                    .stream()
                    .map(this::convertToResponse)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error retrieving papers by department {}: {}", department, e.getMessage());
            return List.of();
        }
    }

    @Override
    public List<PaperSubmissionResponse> searchPapersByTitle(String query) {
        try {
            return paperSubmissionRepository.findByPaperTitleContainingIgnoreCase(query)
                    .stream()
                    .map(this::convertToResponse)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error searching papers by title {}: {}", query, e.getMessage());
            return List.of();
        }
    }

    @Override
    public byte[] downloadPaper(String id) {
        try {
            PaperSubmission paper = paperSubmissionRepository.findByPaperId(id)
                    .orElseThrow(() -> new RuntimeException("Paper not found with ID: " + id));
            return blobStorageService.downloadFile(paper.getPaperFileName());
        } catch (Exception e) {
            logger.error("Error downloading paper with ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Error downloading paper", e);
        }
    }

    @Override
    public PaperSubmissionResponse updatePaperStatus(String paperId, PaperStatus status) {
        try {
            PaperSubmission paper = paperSubmissionRepository.findByPaperId(paperId)
                    .orElseThrow(() -> new RuntimeException("Paper not found with ID: " + paperId));

            paper.setStatus(status);
            PaperSubmission updatedPaper = paperSubmissionRepository.save(paper);
            logger.info("Paper status updated successfully for ID: {}", paperId);
            return convertToResponse(updatedPaper);
        } catch (Exception e) {
            logger.error("Error updating paper status for ID {}: {}", paperId, e.getMessage());
            throw new RuntimeException("Error updating paper status", e);
        }
    }
    @Override
    public List<PaperSubmissionResponse> getPapersByEvaluator(User evaluator) {
        return paperSubmissionRepository.findByEvaluator(evaluator)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaperSubmissionResponse> getPapersByEvaluatorUsername(String evaluatorUsername) {
        User evaluator = userRepository.findByUsername(evaluatorUsername)
                .orElseThrow(() -> new RuntimeException("Evaluator not found"));

        return paperSubmissionRepository.findByEvaluator(evaluator).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }




    @Override
    public void deletePaper(Long id) {
        try {
            PaperSubmission paper = paperSubmissionRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Paper not found with ID: " + id));

            // Delete file from blob storage
            blobStorageService.deleteFile(paper.getPaperFileName());

            // Delete from database
            paperSubmissionRepository.deleteById(id);
            logger.info("Paper deleted successfully with ID: {}", id);
        } catch (Exception e) {
            logger.error("Error deleting paper with ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Error deleting paper", e);
        }
    }

    public boolean checkIfPaperExists(String paperId) {
        return paperSubmissionRepository.existsByPaperId(paperId);
    }

    public PaperSubmissionResponse getPaperByPaperId(String paperId) {
        return paperSubmissionRepository.findByPaperId(paperId)
                .map(this::convertToResponse)
                .orElseThrow(() -> new RuntimeException("Paper not found"));
    }


    @Override
    public List<PaperSubmissionResponse> getPapersByEmail(String email) {
        try {
            return paperSubmissionRepository.findByEmail(email)
                    .stream()
                    .map(this::convertToResponse)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error retrieving papers by email {}: {}", email, e.getMessage());
            return List.of();
        }
    }

    @Override
    public List<PaperSubmission> findByEvaluator(User evaluator) {
        return List.of();
    }

    /**
     * Convert PaperSubmission entity to Response DTO
     */
    private PaperSubmissionResponse convertToResponse(PaperSubmission paper) {
        PaperSubmissionResponse response = new PaperSubmissionResponse();
        response.setPaperId(paper.getPaperId());
        response.setName(paper.getName());
        response.setEmail(paper.getEmail());
        response.setContactNo(paper.getContactNo());
        response.setDepartment(paper.getDepartment());
        response.setCollegeName(paper.getCollegeName());
        response.setPaperTitle(paper.getPaperTitle());
        response.setPaperAbstract(paper.getPaperAbstract());
        response.setPaperFileName(paper.getPaperFileName());
        response.setPaperFileUrl(paper.getPaperFileUrl());
        response.setSubmittedAt(paper.getSubmittedAt());
        response.setEvaluatorName(paper.getEvaluatorName());
        response.setEvaluatorComments(paper.getEvaluatorComments());
        response.setStatus(paper.getStatus());
        logger.warn("The paper status in convertToResponse is: {}", paper.getStatus());
        return response;
    }
    public String generateUniquePaperId() {
        String paperId;
        do {
            paperId = PaperIdGenerator.generatePaperId();
        } while (paperSubmissionRepository.existsByPaperId(paperId));
        return paperId;
    }

}


