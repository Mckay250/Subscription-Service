package com.adidas.subscriptionservice.dto.responses;

import lombok.Data;

@Data
public class ServiceResponse<T> {
    public String message;
    public T data;

    public ServiceResponse(String message) {
        this.message = message;
    }
}
