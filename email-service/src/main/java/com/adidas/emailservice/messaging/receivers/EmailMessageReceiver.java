package com.adidas.emailservice.messaging.receivers;

import com.adidas.emailservice.dto.EmailMessage;
import com.adidas.emailservice.messaging.CustomMessagingProcessor;
import com.adidas.emailservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(CustomMessagingProcessor.class)
public class EmailMessageReceiver {

    private final EmailService emailService;

    @Autowired
    public EmailMessageReceiver(EmailService emailService) {
        this.emailService = emailService;
    }

    @StreamListener("emailMessage")
    public void receiveEmailMessage(EmailMessage message) {
        emailService.sendEmail(message);
    }
}
