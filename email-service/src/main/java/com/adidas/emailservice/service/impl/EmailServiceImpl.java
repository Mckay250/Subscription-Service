package com.adidas.emailservice.service.impl;

import com.adidas.emailservice.dto.EmailMessage;
import com.adidas.emailservice.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Override
    public void sendEmail(EmailMessage message) {
        LOGGER.info("Sending email to {}", message.getRecipient());
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("Email sent to {}", message.getRecipient());
    }
}
