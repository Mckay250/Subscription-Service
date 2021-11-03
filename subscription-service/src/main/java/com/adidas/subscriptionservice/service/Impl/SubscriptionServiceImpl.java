package com.adidas.subscriptionservice.service.Impl;

import com.adidas.subscriptionservice.dto.requests.SubscriptionCreationRequest;
import com.adidas.subscriptionservice.dto.responses.ServiceResponse;
import com.adidas.subscriptionservice.dto.responses.SubscriptionResponse;
import com.adidas.subscriptionservice.enums.SubscriptionState;
import com.adidas.subscriptionservice.messaging.MessagingService;
import com.adidas.subscriptionservice.model.Subscription;
import com.adidas.subscriptionservice.repositories.SubscriptionRepository;
import com.adidas.subscriptionservice.service.SubscriptionService;
import com.adidas.subscriptionservice.utils.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private MessagingService messagingService;


    @Override
    public ServiceResponse<SubscriptionResponse> createSubscription(SubscriptionCreationRequest request) {
        ServiceResponse<SubscriptionResponse> serviceResponse = new ServiceResponse<>(Messages.GENERAL_SUCCESS_MESSAGE);
        Subscription newSubscription = subscriptionRepository.save(new Subscription(request));
        serviceResponse.setData(new SubscriptionResponse(newSubscription));
        messagingService.sendEmail(request.getEmail(), Messages.SUBSCRIPTION_CREATED_EMAIL_SUBJECT, Messages.SUBSCRIPTION_CREATED_EMAIL);
        return serviceResponse;
    }

    @Override
    public ServiceResponse<SubscriptionResponse> cancelSubscription(Long subscriptionId) {
        ServiceResponse<SubscriptionResponse> serviceResponse = new ServiceResponse<>(Messages.GENERAL_SUCCESS_MESSAGE);
        Subscription subscription = this.subscriptionRepository.findById(subscriptionId).orElse(null);
        if (subscription == null) {
            serviceResponse.setMessage(String.format(Messages.GENERIC_NOT_FOUND, "Subscription"));
            return serviceResponse;
        }
        subscription.setState(SubscriptionState.CANCELED);
        subscriptionRepository.save(subscription);
        serviceResponse.setData(new SubscriptionResponse(subscription));
        messagingService.sendEmail(subscription.getEmail(), Messages.SUBSCRIPTION_CANCELED_EMAIL_SUBJECT, Messages.SUBSCRIPTION_CANCELED_EMAIL);
        return serviceResponse;
    }

    @Override
    public ServiceResponse<SubscriptionResponse> getSubscription(Long id) {
        ServiceResponse<SubscriptionResponse> serviceResponse = new ServiceResponse<>(Messages.GENERAL_SUCCESS_MESSAGE);
        Subscription subscription = this.subscriptionRepository.findById(id).orElse(null);
        if (subscription == null) {
            serviceResponse.setMessage(String.format(Messages.GENERIC_NOT_FOUND, "Subscription"));
            return serviceResponse;
        }
        serviceResponse.setData(new SubscriptionResponse(subscription));
        return serviceResponse;
    }

    @Override
    public ServiceResponse<List<SubscriptionResponse>> listAllSubscriptions() {
        ServiceResponse<List<SubscriptionResponse>> serviceResponse = new ServiceResponse<>(Messages.GENERAL_SUCCESS_MESSAGE);
        serviceResponse.setData(this.subscriptionRepository.findAll().stream().map(SubscriptionResponse::new).collect(Collectors.toList()));
        return serviceResponse;
    }


}
