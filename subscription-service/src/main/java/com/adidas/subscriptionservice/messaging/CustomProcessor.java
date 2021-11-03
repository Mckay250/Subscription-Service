package com.adidas.subscriptionservice.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CustomProcessor {

    @Output
    MessageChannel emailMessage();
}
