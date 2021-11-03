package com.adidas.subscriptionservice.dto.requests;

import lombok.Data;

@Data
public class EmailMessage {
    String recipient;
    String subject;
    String message;
}
