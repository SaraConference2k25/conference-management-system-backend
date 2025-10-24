package com.saraconference.backend.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    private boolean isActive = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    // Relationships (optional one-to-one profiles)
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private ParticipantProfile participantProfile;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private EvaluatorProfile evaluatorProfile;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private AdminProfile adminProfile;

    // Getters and setters
    public Long getUserId() { return userId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }

    public Set<Role> getRoles() { return roles; }
    public void setRoles(Set<Role> roles) { this.roles = roles; }

    public ParticipantProfile getParticipantProfile() { return participantProfile; }
    public void setParticipantProfile(ParticipantProfile participantProfile) {
        this.participantProfile = participantProfile;
        participantProfile.setUser(this);
    }

    public EvaluatorProfile getEvaluatorProfile() { return evaluatorProfile; }
    public void setEvaluatorProfile(EvaluatorProfile evaluatorProfile) {
        this.evaluatorProfile = evaluatorProfile;
        evaluatorProfile.setUser(this);
    }

    public AdminProfile getAdminProfile() { return adminProfile; }
    public void setAdminProfile(AdminProfile adminProfile) {
        this.adminProfile = adminProfile;
        adminProfile.setUser(this);
    }
}
