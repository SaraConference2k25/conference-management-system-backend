package com.saraconference.backend.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "paper_submissions")
public class PaperSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Column(nullable = false, length = 500)
    private String paperFileUrl;

    @Column(nullable = false)
    private LocalDateTime submittedAt;


    // Constructors
    public PaperSubmission() {
        this.submittedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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
}
