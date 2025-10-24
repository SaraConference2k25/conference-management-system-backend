package com.saraconference.backend.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "participant_profiles")
public class ParticipantProfile {

    @Id
    private Long id; // same as user_id

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    private String fullName;
    private String collegeName;
    private String projectTitle;
    private String submissionLink;
    private Double score;
    private LocalDateTime registeredAt = LocalDateTime.now();

    // Getters and setters
    public Long getId() { return id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getCollegeName() { return collegeName; }
    public void setCollegeName(String collegeName) { this.collegeName = collegeName; }

    public String getProjectTitle() { return projectTitle; }
    public void setProjectTitle(String projectTitle) { this.projectTitle = projectTitle; }

    public String getSubmissionLink() { return submissionLink; }
    public void setSubmissionLink(String submissionLink) { this.submissionLink = submissionLink; }

    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }

    public LocalDateTime getRegisteredAt() { return registeredAt; }
    public void setRegisteredAt(LocalDateTime registeredAt) { this.registeredAt = registeredAt; }
}
