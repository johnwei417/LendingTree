package com.honglin.service;

public interface EmailService {
    void sendEmail(String toEmail, String subject, String message);
}
