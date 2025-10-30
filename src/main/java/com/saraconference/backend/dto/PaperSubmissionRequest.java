package com.saraconference.backend.dto;

public class PaperSubmissionRequest {
    private String paperTitle;
    private String paperAbstract;
    private String department;
    private String collegeName;

    // Constructors
    public PaperSubmissionRequest() {}

    public PaperSubmissionRequest(String paperTitle, String paperAbstract, String department, String collegeName) {
        this.paperTitle = paperTitle;
        this.paperAbstract = paperAbstract;
        this.department = department;
        this.collegeName = collegeName;
    }

    // Getters and Setters
    public String getPaperTitle() { return paperTitle; }
    public void setPaperTitle(String paperTitle) { this.paperTitle = paperTitle; }

    public String getPaperAbstract() { return paperAbstract; }
    public void setPaperAbstract(String paperAbstract) { this.paperAbstract = paperAbstract; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getCollegeName() { return collegeName; }
    public void setCollegeName(String collegeName) { this.collegeName = collegeName; }
}
