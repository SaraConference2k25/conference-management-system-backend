package com.saraconference.backend.service;

public interface EmailService {
    void sendEmailAsync(String to, String subject, String body);
    void sendAcceptanceEmail(String to, String subject);
    void sendRejectionEmail(String to, String subject);

}
