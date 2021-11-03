package com.adidas.emailservice.service;

import com.adidas.emailservice.dto.EmailMessage;

public interface EmailService {
    void sendEmail(EmailMessage message);
}
