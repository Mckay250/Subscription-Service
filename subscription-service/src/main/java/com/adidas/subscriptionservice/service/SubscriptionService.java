package com.adidas.subscriptionservice.service;

import com.adidas.subscriptionservice.dto.requests.SubscriptionCreationRequest;
import com.adidas.subscriptionservice.dto.responses.ServiceResponse;
import com.adidas.subscriptionservice.dto.responses.SubscriptionResponse;

import java.util.List;

public interface SubscriptionService {

    ServiceResponse<SubscriptionResponse> createSubscription(SubscriptionCreationRequest request);

    ServiceResponse<SubscriptionResponse> cancelSubscription(Long subscriptionId);

    ServiceResponse<SubscriptionResponse> getSubscription(Long id);

    ServiceResponse<List<SubscriptionResponse>> listAllSubscriptions();
}
