package com.saraconference.backend.controller;

import com.saraconference.backend.dto.CreateEvaluatorRequest;
import com.saraconference.backend.dto.EvaluatorResponse;
import com.saraconference.backend.dto.PaperSubmissionRequest;
import com.saraconference.backend.dto.PaperSubmissionResponse;
import com.saraconference.backend.service.EvaluatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/evaluators")
@PreAuthorize("hasRole('ADMIN')")
public class EvaluatorController {
    private static final Logger logger = LoggerFactory.getLogger(EvaluatorController.class);
    @Autowired
    private EvaluatorService evaluatorService;

    @PostMapping("/create")
    public ResponseEntity<?> createEvaluator(@RequestBody CreateEvaluatorRequest request) {
        try {
            EvaluatorResponse response = evaluatorService.createEvaluator(request);
            logger.info("Evaluator created with ID: {}", response.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new HashMap<String, String>() {{
                        put("error", e.getMessage());
                    }});
        }
    }
    @GetMapping("/manage")
    public ResponseEntity<?> getEvaluatorsForManagement() {
        try {
            List<EvaluatorResponse> evaluators = evaluatorService.getAllEvaluators();
            return ResponseEntity.ok(evaluators);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new HashMap<String, String>() {{
                        put("error", e.getMessage());
                    }});
        }
    }


    @GetMapping("/all")
    public ResponseEntity<?> getAllEvaluators() {
        try {
            List<EvaluatorResponse> evaluators = evaluatorService.getAllEvaluators();
            return ResponseEntity.ok(evaluators);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new HashMap<String, String>() {{
                        put("error", e.getMessage());
                    }});
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEvaluatorById(@PathVariable Long id) {
        try {
            EvaluatorResponse evaluator = evaluatorService.getEvaluatorById(id);
            return ResponseEntity.ok(evaluator);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new HashMap<String, String>() {{
                        put("error", e.getMessage());
                    }});
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEvaluator(@PathVariable Long id,
                                             @RequestBody CreateEvaluatorRequest request) {
        try {
            EvaluatorResponse response = evaluatorService.updateEvaluator(id, request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new HashMap<String, String>() {{
                        put("error", e.getMessage());
                    }});
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvaluator(@PathVariable Long id) {
        try {
            evaluatorService.deleteEvaluator(id);
            return ResponseEntity.ok(new HashMap<String, String>() {{
                put("message", "Evaluator deleted successfully");
            }});
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new HashMap<String, String>() {{
                        put("error", e.getMessage());
                    }});
        }
    }

    @GetMapping("/stats/summary")
    public ResponseEntity<?> getEvaluatorStats() {
        try {
            Map<String, Long> stats = new HashMap<>();
            stats.put("totalEvaluators", evaluatorService.getTotalEvaluators());
            stats.put("activeEvaluators", evaluatorService.getActiveEvaluators());
            stats.put("availableEvaluators", evaluatorService.getAvailableEvaluators());
            return ResponseEntity.ok(stats);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new HashMap<String, String>() {{
                        put("error", e.getMessage());
                    }});
        }
    }

    @PostMapping("/evaluate-paper")
    public ResponseEntity<PaperSubmissionResponse> evaluatePaper(@RequestBody PaperSubmissionRequest request){
       PaperSubmissionResponse response =  evaluatorService.evaluatePaper(request);
        return ResponseEntity.ok().body(response);
    }
}
