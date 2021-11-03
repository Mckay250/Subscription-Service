package com.adidas.subscriptionservice.dto.responses;

import com.adidas.subscriptionservice.enums.Gender;
import com.adidas.subscriptionservice.enums.SubscriptionState;
import com.adidas.subscriptionservice.model.Subscription;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SubscriptionResponse {

    private Long id;
    private String email;
    private String firstName;
    private Gender gender;
    private LocalDate dateOfBirth;
    private boolean consent;
    private String newsLetterId;
    private SubscriptionState state;

    public SubscriptionResponse(Subscription subscription) {
        this.id = subscription.getId();
        this.email = subscription.getEmail();
        this.firstName = subscription.getFirstName();
        this.gender = subscription.getGender();
        this.dateOfBirth = subscription.getDateOfBirth();
        this.consent = subscription.isConsent();
        this.newsLetterId = subscription.getNewsLetterId();
        this.state = subscription.getState();
    }
}
