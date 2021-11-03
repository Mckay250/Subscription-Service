package com.adidas.emailservice.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface CustomMessagingProcessor {

    @Input
    SubscribableChannel emailMessage();

}
