package com.example.ip_backend.services;

public interface EmailService {
    void sendEmail(String to, String subject, String body);
}