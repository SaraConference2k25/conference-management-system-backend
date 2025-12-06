package com.saraconference.backend.service.impl;

import com.saraconference.backend.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmailAsync(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
        logger.info("Email sent to {}", to);
    }

    @Override
    @Async
    public void sendAcceptanceEmail(String email, String paperTitle,String evaluatorComments) {
        String subject = "Paper Submission Accepted";
        String body = "Congratulations! Your paper titled '" + paperTitle + "' has been accepted.";
        if(evaluatorComments != null && !evaluatorComments.isBlank()){
            body += "\n\nEvaluator Comments:\n" + evaluatorComments;
        }
        body += "\n\nWe look forward to your participation in the conference.\n\nWith regards, \nSara Conference Committee";
        sendEmailAsync(email, subject, body);
    }

    @Override
    @Async
    public void sendRejectionEmail(String email, String paperTitle,String evaluatorComments) {
        String subject = "Paper Submission Rejected";
        String body = "We regret to inform you that your paper titled '" + paperTitle + "' has been rejected.";
        if(evaluatorComments != null && !evaluatorComments.isBlank()){
            body += "\n\nEvaluator Comments:\n" + evaluatorComments;
        }
        body += "\n\nThank you for your interest in our conference.\n\nWith regards, \nSara Conference Committee";
        sendEmailAsync(email, subject, body);
    }
}
