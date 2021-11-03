package com.adidas.subscriptionservice.controller;

import com.adidas.subscriptionservice.dto.requests.SubscriptionCreationRequest;
import com.adidas.subscriptionservice.dto.responses.ServiceResponse;
import com.adidas.subscriptionservice.dto.responses.SubscriptionResponse;
import com.adidas.subscriptionservice.service.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/subscriptions")
public class SubscriptionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionController.class);

    @Autowired
    private SubscriptionService subscriptionService;

    @RequestMapping(path = "test", method = RequestMethod.POST)
    public ResponseEntity<String> test() {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body("Welcome to Subscriptions service");
        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<ServiceResponse<SubscriptionResponse>> createSubscription(@RequestBody SubscriptionCreationRequest request) {
        try {
            ServiceResponse<SubscriptionResponse> response = subscriptionService.createSubscription(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @RequestMapping(path = "{subscriptionId}", method = RequestMethod.PATCH)
    public ResponseEntity<ServiceResponse<SubscriptionResponse>> cancelSubscription(@PathVariable(name = "subscriptionId") Long id) {
        try {
            ServiceResponse<SubscriptionResponse> response = subscriptionService.cancelSubscription(id);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @RequestMapping(path = "{subscriptionId}", method = RequestMethod.GET)
    public ResponseEntity<ServiceResponse<SubscriptionResponse>> getById(@PathVariable(name = "subscriptionId") Long id) {
        try {
            ServiceResponse<SubscriptionResponse> response = subscriptionService.getSubscription(id);
            if (response.getData() == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<ServiceResponse<List<SubscriptionResponse>>> listAll() {
        try {
            ServiceResponse<List<SubscriptionResponse>> response = subscriptionService.listAllSubscriptions();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}
