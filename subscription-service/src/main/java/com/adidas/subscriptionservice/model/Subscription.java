package com.adidas.subscriptionservice.model;

import com.adidas.subscriptionservice.dto.requests.SubscriptionCreationRequest;
import com.adidas.subscriptionservice.enums.Gender;
import com.adidas.subscriptionservice.enums.SubscriptionState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Subscription {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String firstName;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private LocalDate dateOfBirth;
    private boolean consent;
    private String newsLetterId;
    @Enumerated(EnumType.STRING)
    private SubscriptionState state;



    public Subscription(SubscriptionCreationRequest request) {
        this.email = request.getEmail();
        this.firstName = request.getFirstName();
        this.gender = request.getGender();
        this.dateOfBirth = request.getDateOfBirth();
        this.consent = request.isConsent();
        this.newsLetterId = request.getNewsLetterId();
        this.state = SubscriptionState.SUBSCRIBED;
    }
}
