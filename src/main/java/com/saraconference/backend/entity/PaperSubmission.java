package com.saraconference.backend.entity;

import com.saraconference.backend.enums.PaperStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "paper_submissions", indexes = {
        @Index(name = "idx_paper_id", columnList = "paper_id"),
        @Index(name = "idx_email", columnList = "email"),
        @Index(name = "idx_department", columnList = "department"),
        @Index(name = "idx_status", columnList = "status"),
        @Index(name = "idx_evaluator_id", columnList = "evaluator_id"),
        @Index(name = "idx_paper_title", columnList = "paper_title"),
        @Index(name = "idx_submitted_at", columnList = "submitted_at"),
        @Index(name = "idx_evaluator_status", columnList = "evaluator_id, status")
})
public class PaperSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20, unique = true)
    private String paperId;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, length = 255)
    private String email;

    @Column(nullable = false, length = 20)
    private String contactNo;

    @Column(nullable = false, length = 100)
    private String department;

    @Column(nullable = false, length = 255)
    private String collegeName;

    @Column(nullable = false, length = 255)
    private String paperTitle;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String paperAbstract;

    @Column(nullable = false, length = 255)
    private String paperFileName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private PaperStatus status = PaperStatus.PENDING_ASSIGNMENT;

    @Column(nullable = false, length = 500)
    private String paperFileUrl;

    @Column(nullable = false)
    private LocalDateTime submittedAt;

    @ManyToOne
    @JoinColumn(name = "evaluator_id")
    private User evaluator;

    @Column
    private String evaluatorName;

    @Column
    private String evaluatorComments;

    public PaperSubmission() {
        this.submittedAt = LocalDateTime.now();
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPaperId() { return paperId; }
    public void setPaperId(String paperId) { this.paperId = paperId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getContactNo() { return contactNo; }
    public void setContactNo(String contactNo) { this.contactNo = contactNo; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getCollegeName() { return collegeName; }
    public void setCollegeName(String collegeName) { this.collegeName = collegeName; }

    public String getPaperTitle() { return paperTitle; }
    public void setPaperTitle(String paperTitle) { this.paperTitle = paperTitle; }

    public String getPaperAbstract() { return paperAbstract; }
    public void setPaperAbstract(String paperAbstract) { this.paperAbstract = paperAbstract; }

    public String getPaperFileName() { return paperFileName; }
    public void setPaperFileName(String paperFileName) { this.paperFileName = paperFileName; }

    public String getPaperFileUrl() { return paperFileUrl; }
    public void setPaperFileUrl(String paperFileUrl) { this.paperFileUrl = paperFileUrl; }

    public LocalDateTime getSubmittedAt() { return submittedAt; }
    public void setSubmittedAt(LocalDateTime submittedAt) { this.submittedAt = submittedAt; }

    public User getEvaluator() { return evaluator; }
    public void setEvaluator(User evaluator) { this.evaluator = evaluator; }

    public PaperStatus getStatus() { return status; }
    public void setStatus(PaperStatus status) { this.status = status; }

    public String getEvaluatorName() { return evaluatorName; }
    public void setEvaluatorName(String evaluatorName) { this.evaluatorName = evaluatorName; }

    public String getEvaluatorComments() { return evaluatorComments; }
    public void setEvaluatorComments(String evaluatorComments) { this.evaluatorComments = evaluatorComments; }
}
