package com.saraconference.backend.dto;

import java.time.LocalDateTime;

public class PaperSubmissionResponse {
    private Long id;
    private String name;
    private String email;
    private String contactNo;
    private String department;
    private String collegeName;
    private String paperTitle;
    private String paperAbstract;
    private String paperFileName;
    private String paperFileUrl;
    private LocalDateTime submittedAt;

    // Constructors
    public PaperSubmissionResponse() {}

    public PaperSubmissionResponse(Long id, String name, String email, String paperTitle, LocalDateTime submittedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.paperTitle = paperTitle;
        this.submittedAt = submittedAt;
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
