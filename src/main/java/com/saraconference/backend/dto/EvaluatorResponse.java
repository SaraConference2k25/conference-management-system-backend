package com.saraconference.backend.dto;

public class EvaluatorResponse {
    private Long id;
    private String evaluatorName;
    private String email;
    private String username;
    private String department;
    private String status;
    private Integer workload;
    private String avatarInitial;

    public EvaluatorResponse(Long id, String evaluatorName, String email, String username,
                             String department, String status, Integer workload) {
        this.id = id;
        this.evaluatorName = evaluatorName;
        this.email = email;
        this.username = username;
        this.department = department;
        this.status = status;
        this.workload = workload;
        this.avatarInitial = evaluatorName != null && !evaluatorName.isEmpty()
                ? String.valueOf(evaluatorName.charAt(0)).toUpperCase()
                : "E";
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEvaluatorName() { return evaluatorName; }
    public void setEvaluatorName(String evaluatorName) { this.evaluatorName = evaluatorName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Integer getWorkload() { return workload; }
    public void setWorkload(Integer workload) { this.workload = workload; }

    public String getAvatarInitial() { return avatarInitial; }
    public void setAvatarInitial(String avatarInitial) { this.avatarInitial = avatarInitial; }
}
