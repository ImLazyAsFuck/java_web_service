package com.ss10.service.mail;

public interface MailService {
    void sendEmail(String to, String subject, String content);
}
