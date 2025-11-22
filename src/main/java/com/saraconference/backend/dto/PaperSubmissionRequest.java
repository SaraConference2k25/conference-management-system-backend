package com.saraconference.backend.dto;


import com.saraconference.backend.entity.User;
import com.saraconference.backend.enums.PaperStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaperSubmissionRequest {
    private String paperId;
    private String paperTitle;
    private String paperAbstract;
    private String department;
    private String collegeName;
    private User evaluator;
    private String evaluatorComments;
    private PaperStatus status;

    // Constructors
    public PaperSubmissionRequest() {}

    public PaperSubmissionRequest(String paperTitle, String paperAbstract, String department, String collegeName) {
        this.paperTitle = paperTitle;
        this.paperAbstract = paperAbstract;
        this.department = department;
        this.collegeName = collegeName;
    }


}
