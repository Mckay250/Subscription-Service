package com.adidas.emailservice.dto;

import lombok.Data;

@Data
public class EmailMessage {
    String recipient;
    String subject;
    String message;
}
