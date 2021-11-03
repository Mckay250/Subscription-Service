package com.adidas.subscriptionservice.messaging;

import com.adidas.subscriptionservice.dto.requests.EmailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class MessagingService {

    @Autowired
    private MessageChannel emailMessage;

    public void sendEmail(String recipient, String subject, String message) {
        EmailMessage messageRequest = new EmailMessage();
        messageRequest.setMessage(message);
        messageRequest.setRecipient(message);
        messageRequest.setSubject(subject);
        this.emailMessage.send(MessageBuilder.withPayload(messageRequest).build());
    }

}
