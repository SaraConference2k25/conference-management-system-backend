package com.saraconference.backend.controller;

import com.saraconference.backend.dto.PaperSubmissionResponse;
import com.saraconference.backend.service.PaperSubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/papers")
@CrossOrigin(origins = "*")
public class PaperSubmissionController {

    @Autowired
    private PaperSubmissionService paperSubmissionService;

    /**
     * Test endpoint
     */
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Paper submission API is working!");
    }

    /**
     * Submit a new paper
     * Form parameters: name, email, contactNo, department, collegeName, paperTitle, paperAbstract, paperFile
     */
    @PostMapping("/submit")
    public ResponseEntity<?> submitPaper(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("contactNo") String contactNo,
            @RequestParam("department") String department,
            @RequestParam("collegeName") String collegeName,
            @RequestParam("paperTitle") String paperTitle,
            @RequestParam("paperAbstract") String paperAbstract,
            @RequestParam("paperFile") MultipartFile paperFile) {
        try {
            PaperSubmissionResponse response = paperSubmissionService.submitPaper(
                    name, email, contactNo, department, collegeName,
                    paperTitle, paperAbstract, paperFile);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error submitting paper: " + e.getMessage());
        }
    }

    /**
     * Get all papers
     */
    @GetMapping("/all")
    public ResponseEntity<?> getAllPapers() {
        try {
            List<PaperSubmissionResponse> papers = paperSubmissionService.getAllPapers();
            return ResponseEntity.ok(papers);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error retrieving papers: " + e.getMessage());
        }
    }

    /**
     * Get paper by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getPaperById(@PathVariable Long id) {
        try {
            PaperSubmissionResponse paper = paperSubmissionService.getPaperById(id);
            return ResponseEntity.ok(paper);
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Paper not found: " + e.getMessage());
        }
    }

    /**
     * Download paper file
     */
    @GetMapping("/download/{id}")
    public ResponseEntity<?> downloadPaper(@PathVariable Long id) {
        try {
            byte[] fileContent = paperSubmissionService.downloadPaper(id);
            PaperSubmissionResponse paper = paperSubmissionService.getPaperById(id);

            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=" + paper.getPaperFileName())
                    .header("Content-Type", "application/pdf")
                    .body(fileContent);
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Error downloading paper: " + e.getMessage());
        }
    }

    /**
     * Search papers by title
     */
    @GetMapping("/search")
    public ResponseEntity<?> searchPapers(@RequestParam("query") String query) {
        try {
            List<PaperSubmissionResponse> papers = paperSubmissionService.searchPapersByTitle(query);
            return ResponseEntity.ok(papers);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error searching papers: " + e.getMessage());
        }
    }

    /**
     * Get papers by department
     */
    @GetMapping("/department/{department}")
    public ResponseEntity<?> getPapersByDepartment(@PathVariable String department) {
        try {
            List<PaperSubmissionResponse> papers = paperSubmissionService.getPapersByDepartment(department);
            return ResponseEntity.ok(papers);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error retrieving papers: " + e.getMessage());
        }
    }

    /**
     * Get papers by email
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getPapersByEmail(@PathVariable String email) {
        try {
            List<PaperSubmissionResponse> papers = paperSubmissionService.getPapersByEmail(email);
            return ResponseEntity.ok(papers);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error retrieving papers: " + e.getMessage());
        }
    }

    /**
     * Delete paper
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePaper(@PathVariable Long id) {
        try {
            paperSubmissionService.deletePaper(id);
            return ResponseEntity.ok("Paper deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Error deleting paper: " + e.getMessage());
        }
    }

    /**
     * Assign evaluator to a paper
     */
    @PostMapping("/{paperId}/assign-evaluator/{evaluatorId}")
    public ResponseEntity<?> assignEvaluatorToPaper(
            @PathVariable Long paperId,
            @PathVariable Long evaluatorId) {
        try {
            paperSubmissionService.assignEvaluatorToPaper(paperId, evaluatorId);
            return ResponseEntity.ok("Evaluator assigned successfully");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error assigning evaluator: " + e.getMessage());
        }
    }

}
