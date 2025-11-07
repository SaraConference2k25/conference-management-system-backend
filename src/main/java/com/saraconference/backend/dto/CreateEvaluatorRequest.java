package com.saraconference.backend.dto;

public class CreateEvaluatorRequest {
    private String email;
    private String username;
    private String password;
    private String confirmPassword;
    private String department;

    public CreateEvaluatorRequest() {}

    public CreateEvaluatorRequest(String email, String username, String password,
                                  String confirmPassword, String department) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.department = department;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getConfirmPassword() { return confirmPassword; }
    public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
}
