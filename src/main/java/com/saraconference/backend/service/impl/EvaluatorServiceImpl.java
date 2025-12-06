package com.saraconference.backend.service.impl;

import com.saraconference.backend.dto.CreateEvaluatorRequest;
import com.saraconference.backend.dto.EvaluatorResponse;
import com.saraconference.backend.dto.PaperSubmissionRequest;
import com.saraconference.backend.dto.PaperSubmissionResponse;
import com.saraconference.backend.entity.PaperSubmission;
import com.saraconference.backend.entity.Role;
import com.saraconference.backend.entity.User;
import com.saraconference.backend.repository.RoleRepository;
import com.saraconference.backend.repository.UserRepository;
import com.saraconference.backend.repository.PaperSubmissionRepository;
import com.saraconference.backend.service.EvaluatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.saraconference.backend.service.EmailService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EvaluatorServiceImpl implements EvaluatorService {
    private Logger logger = LoggerFactory.getLogger(EvaluatorServiceImpl.class);

    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PaperSubmissionRepository paperRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public EvaluatorResponse createEvaluator(CreateEvaluatorRequest request) {
        String password = request.getPassword() != null ? request.getPassword().trim() : "";

        logger.debug("Password validation - Password length: {}", password.length());

        // Only validate password, not confirmPassword since frontend doesn't send it
        if (password.isEmpty()) {
            throw new RuntimeException("Password cannot be empty");
        }

        if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
            throw new RuntimeException("Email cannot be empty");
        }

        if (request.getUsername() == null || request.getUsername().trim().isEmpty()) {
            throw new RuntimeException("Username cannot be empty");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already in use");
        }

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already taken");
        }

        Role evaluatorRole = roleRepository.findByRoleName("EVALUATOR")
                .orElseThrow(() -> new RuntimeException("Evaluator role not found"));

        User evaluator = new User();
        evaluator.setUsername(request.getUsername());
        evaluator.setEmail(request.getEmail());
        evaluator.setPassword(passwordEncoder.encode(password));
        evaluator.setDepartment(request.getDepartment());
        evaluator.getRoles().add(evaluatorRole);
        evaluator.setCreatedAt(LocalDateTime.now());
        evaluator.setUpdatedAt(LocalDateTime.now());

        User savedEvaluator = userRepository.save(evaluator);
        logger.info("Evaluator created: {} with email: {} and department: {}",
                request.getUsername(), request.getEmail(), request.getDepartment());

        return new EvaluatorResponse(
                savedEvaluator.getUserId(),
                savedEvaluator.getUsername(),
                savedEvaluator.getEmail(),
                savedEvaluator.getUsername(),
                savedEvaluator.getDepartment(),
                "Active",
                0
        );
    }

    @Override
    public List<EvaluatorResponse> getAllEvaluators() {
        Role evaluatorRole = roleRepository.findByRoleName("EVALUATOR")
                .orElseThrow(() -> new RuntimeException("Evaluator role not found"));

        return userRepository.findAll().stream()
                .filter(user -> user.getRoles().contains(evaluatorRole))
                .map(user -> new EvaluatorResponse(
                        user.getUserId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getUsername(),
                        user.getDepartment(),
                        "Active",
                        user.getWorkload() != null ? user.getWorkload() : 0
                ))
                .collect(Collectors.toList());
    }

    @Override
    public EvaluatorResponse getEvaluatorById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluator not found"));

        return new EvaluatorResponse(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getUsername(),
                user.getDepartment(),
                "Active",
                user.getWorkload() != null ? user.getWorkload() : 0
        );
    }

    @Override
    public EvaluatorResponse updateEvaluator(Long id, CreateEvaluatorRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluator not found"));

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setDepartment(request.getDepartment());
        user.setUpdatedAt(LocalDateTime.now());

        User updatedUser = userRepository.save(user);
        logger.info("Evaluator updated: {}", request.getUsername());

        return new EvaluatorResponse(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getUsername(),
                user.getDepartment(),
                "Active",
                user.getWorkload() != null ? user.getWorkload() : 0
        );
    }

    @Override
    public void deleteEvaluator(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluator not found"));
        userRepository.delete(user);
        logger.info("Evaluator deleted: {}", user.getUsername());
    }

    @Override
    public long getTotalEvaluators() {
        Role evaluatorRole = roleRepository.findByRoleName("EVALUATOR")
                .orElseThrow(() -> new RuntimeException("Evaluator role not found"));
        return userRepository.findAll().stream()
                .filter(user -> user.getRoles().contains(evaluatorRole))
                .count();
    }

    @Override
    public long getActiveEvaluators() {
        return getTotalEvaluators();
    }

    @Override
    public long getAvailableEvaluators() {
        return getTotalEvaluators();
    }

    @Override
    public void assignEvaluatorToPaper(Long paperId, Long evaluatorId) {
        PaperSubmission paper = paperRepository.findById(paperId)
                .orElseThrow(() -> new RuntimeException("Paper not found"));

        User evaluator = userRepository.findById(evaluatorId)
                .orElseThrow(() -> new RuntimeException("Evaluator not found"));

        Role evaluatorRole = roleRepository.findByRoleName("EVALUATOR")
                .orElseThrow(() -> new RuntimeException("Evaluator role not found"));

        if (!evaluator.getRoles().contains(evaluatorRole)) {
            throw new RuntimeException("User is not an evaluator");
        }

        paper.setEvaluator(evaluator);
        paperRepository.save(paper);
    }

    @Override
    public List<PaperSubmissionResponse> getPapersByEvaluatorUsername(String evaluatorUsername) {
        User evaluator = userRepository.findByUsername(evaluatorUsername)
                .orElseThrow(() -> new RuntimeException("Evaluator not found"));

        return paperRepository.findByEvaluator(evaluator).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }



    @Override
    public List<PaperSubmissionResponse> getPapersAssignedToEvaluator(Long evaluatorId) {
        User evaluator = userRepository.findById(evaluatorId)
                .orElseThrow(() -> new RuntimeException("Evaluator not found"));

        return paperRepository.findByEvaluator(evaluator).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }



    private PaperSubmissionResponse convertToResponse(PaperSubmission paper) {
        return new PaperSubmissionResponse(
                paper.getId(),
                paper.getName(),
                paper.getPaperAbstract(),
                paper.getName(),
                paper.getStatus(),
                paper.getEvaluator() != null ? paper.getEvaluator().getUsername() : null,
                paper.getSubmittedAt()
        );
    }
    @Override
    public PaperSubmissionResponse evaluatePaper(PaperSubmissionRequest request) {
        PaperSubmission paper = paperRepository.findByPaperId(request.getPaperId())
                .orElseThrow(() -> new RuntimeException("Paper not found"));
        logger.info("The paper to be evaluated: {}", paper.getPaperTitle());

        User evaluator = userRepository.findById(paper.getEvaluator().getUserId())
                .orElseThrow(() -> new RuntimeException("Evaluator not found"));
        logger.info("The evaluator evaluating is : {}", evaluator.getUsername());
        paper.setEvaluatorComments(request.getEvaluatorComments());
        paper.setStatus(request.getStatus());
        logger.info("Paper {} status set to {}", paper.getPaperTitle(), request.getStatus());
        paperRepository.save(paper);
        logger.info("Paper {} evaluated by {}", paper.getPaperTitle(), evaluator.getUsername());
        switch (request.getStatus()) {
            case ACCEPTED:
                logger.info("Paper {} has been accepted.", paper.getPaperTitle());
                emailService.sendAcceptanceEmail(paper.getEmail(), paper.getPaperTitle(), paper.getEvaluatorComments());
                break;
            case REJECTED:
                emailService.sendRejectionEmail(paper.getEmail(), paper.getPaperTitle(), paper.getEvaluatorComments());
                logger.info("Paper {} has been rejected.", paper.getPaperTitle());
                break;
            default:
                logger.info("Paper {} status updated to {}.", paper.getPaperTitle(), request.getStatus());
                break;
        }
        logger.debug("The evaluator workload before evaluation is : {}", evaluator.getWorkload());
        if(evaluator.getWorkload() <= 0){
            evaluator.setWorkload(0);
        } else {
            evaluator.setWorkload(evaluator.getWorkload() - 1);
        }
        logger.debug("The evaluvator workload after evaluation is : {}", evaluator.getWorkload());
        userRepository.save(evaluator);
        paperRepository.save(paper);
        return convertToResponse(paper);
    }
}
