package com.saraconference.backend.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "evaluator_profiles")
public class EvaluatorProfile {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    private String fullName;
    private String specialization;
    private String assignedProjects; // could be JSON or comma-separated
    private LocalDateTime joinedAt = LocalDateTime.now();

    // Getters and setters
    public Long getId() { return id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public String getAssignedProjects() { return assignedProjects; }
    public void setAssignedProjects(String assignedProjects) { this.assignedProjects = assignedProjects; }

    public LocalDateTime getJoinedAt() { return joinedAt; }
    public void setJoinedAt(LocalDateTime joinedAt) { this.joinedAt = joinedAt; }
}
