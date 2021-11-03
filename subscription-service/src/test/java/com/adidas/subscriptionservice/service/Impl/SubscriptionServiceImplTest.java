package com.adidas.subscriptionservice.service.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.adidas.subscriptionservice.dto.requests.SubscriptionCreationRequest;
import com.adidas.subscriptionservice.dto.responses.ServiceResponse;
import com.adidas.subscriptionservice.dto.responses.SubscriptionResponse;
import com.adidas.subscriptionservice.enums.Gender;
import com.adidas.subscriptionservice.enums.SubscriptionState;
import com.adidas.subscriptionservice.model.Subscription;
import com.adidas.subscriptionservice.repositories.SubscriptionRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SubscriptionServiceImpl.class})
@ExtendWith(SpringExtension.class)
class SubscriptionServiceImplTest {
    @MockBean
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private SubscriptionServiceImpl subscriptionServiceImpl;

    @Test
    void testCreateSubscription() {
        Subscription subscription = new Subscription();
        subscription.setNewsLetterId("42");
        subscription.setEmail("jane.doe@example.org");
        subscription.setDateOfBirth(LocalDate.ofEpochDay(1L));
        subscription.setId(123L);
        subscription.setState(SubscriptionState.SUBSCRIBED);
        subscription.setGender(Gender.MALE);
        subscription.setFirstName("Jane");
        subscription.setConsent(true);
        when(this.subscriptionRepository.save((Subscription) any())).thenReturn(subscription);
        ServiceResponse<SubscriptionResponse> actualCreateSubscriptionResult = this.subscriptionServiceImpl
                .createSubscription(new SubscriptionCreationRequest());
        assertEquals("ServiceResponse(message=Successful, data=SubscriptionResponse(id=123, email=jane.doe@example.org,"
                        + " firstName=Jane, gender=MALE, dateOfBirth=1970-01-02, consent=true, newsLetterId=42, state=CANCELED)" + ")",
                actualCreateSubscriptionResult.toString());
        assertEquals("Successful", actualCreateSubscriptionResult.getMessage());
        SubscriptionResponse data = actualCreateSubscriptionResult.getData();
        assertTrue(data.isConsent());
        assertEquals(SubscriptionState.SUBSCRIBED, data.getState());
        assertEquals("42", data.getNewsLetterId());
        assertEquals(123L, data.getId().longValue());
        assertEquals(Gender.MALE, data.getGender());
        assertEquals("Jane", data.getFirstName());
        assertEquals("jane.doe@example.org", data.getEmail());
        assertEquals("1970-01-02", data.getDateOfBirth().toString());
        verify(this.subscriptionRepository).save((Subscription) any());
    }

    @Test
    void testCreateSubscription2() {
        Subscription subscription = new Subscription();
        subscription.setNewsLetterId("42");
        subscription.setEmail("jane.doe@example.org");
        subscription.setDateOfBirth(LocalDate.ofEpochDay(1L));
        subscription.setId(123L);
        subscription.setState(SubscriptionState.CANCELED);
        subscription.setGender(Gender.MALE);
        subscription.setFirstName("Jane");
        subscription.setConsent(true);
        when(this.subscriptionRepository.save((Subscription) any())).thenReturn(subscription);
        SubscriptionCreationRequest subscriptionCreationRequest = mock(SubscriptionCreationRequest.class);
        when(subscriptionCreationRequest.getNewsLetterId()).thenReturn("42");
        when(subscriptionCreationRequest.isConsent()).thenReturn(true);
        when(subscriptionCreationRequest.getDateOfBirth()).thenReturn(LocalDate.ofEpochDay(1L));
        when(subscriptionCreationRequest.getGender()).thenReturn(Gender.MALE);
        when(subscriptionCreationRequest.getFirstName()).thenReturn("Jane");
        when(subscriptionCreationRequest.getEmail()).thenReturn("jane.doe@example.org");
        ServiceResponse<SubscriptionResponse> actualCreateSubscriptionResult = this.subscriptionServiceImpl
                .createSubscription(subscriptionCreationRequest);
        assertEquals("ServiceResponse(message=Successful, data=SubscriptionResponse(id=123, email=jane.doe@example.org,"
                        + " firstName=Jane, gender=MALE, dateOfBirth=1970-01-02, consent=true, newsLetterId=42, state=CANCELED)" + ")",
                actualCreateSubscriptionResult.toString());
        assertEquals("Successful", actualCreateSubscriptionResult.getMessage());
        SubscriptionResponse data = actualCreateSubscriptionResult.getData();
        assertTrue(data.isConsent());
        assertEquals(SubscriptionState.CANCELED, data.getState());
        assertEquals("42", data.getNewsLetterId());
        assertEquals(123L, data.getId().longValue());
        assertEquals(Gender.MALE, data.getGender());
        assertEquals("Jane", data.getFirstName());
        assertEquals("jane.doe@example.org", data.getEmail());
        assertEquals("1970-01-02", data.getDateOfBirth().toString());
        verify(this.subscriptionRepository).save((Subscription) any());
        verify(subscriptionCreationRequest).getDateOfBirth();
        verify(subscriptionCreationRequest).getEmail();
        verify(subscriptionCreationRequest).getFirstName();
        verify(subscriptionCreationRequest).getGender();
        verify(subscriptionCreationRequest).getNewsLetterId();
        verify(subscriptionCreationRequest).isConsent();
    }

    @Test
    void testCancelSubscription() {
        Subscription subscription = new Subscription();
        subscription.setNewsLetterId("42");
        subscription.setEmail("jane.doe@example.org");
        subscription.setDateOfBirth(LocalDate.ofEpochDay(1L));
        subscription.setId(123L);
        subscription.setState(SubscriptionState.CANCELED);
        subscription.setGender(Gender.MALE);
        subscription.setFirstName("Jane");
        subscription.setConsent(true);
        Optional<Subscription> ofResult = Optional.<Subscription>of(subscription);

        Subscription subscription1 = new Subscription();
        subscription1.setNewsLetterId("42");
        subscription1.setEmail("jane.doe@example.org");
        subscription1.setDateOfBirth(LocalDate.ofEpochDay(1L));
        subscription1.setId(123L);
        subscription1.setState(SubscriptionState.CANCELED);
        subscription1.setGender(Gender.MALE);
        subscription1.setFirstName("Jane");
        subscription1.setConsent(true);
        when(this.subscriptionRepository.save((Subscription) any())).thenReturn(subscription1);
        when(this.subscriptionRepository.findById((Long) any())).thenReturn(ofResult);
        ServiceResponse<SubscriptionResponse> actualCancelSubscriptionResult = this.subscriptionServiceImpl
                .cancelSubscription(123L);
        assertEquals("ServiceResponse(message=Successful, data=SubscriptionResponse(id=123, email=jane.doe@example.org,"
                        + " firstName=Jane, gender=MALE, dateOfBirth=1970-01-02, consent=true, newsLetterId=42, state=CANCELED)" + ")",
                actualCancelSubscriptionResult.toString());
        assertEquals("Successful", actualCancelSubscriptionResult.getMessage());
        SubscriptionResponse data = actualCancelSubscriptionResult.getData();
        assertTrue(data.isConsent());
        assertEquals(SubscriptionState.CANCELED, data.getState());
        assertEquals("42", data.getNewsLetterId());
        assertEquals(123L, data.getId().longValue());
        assertEquals(Gender.MALE, data.getGender());
        assertEquals("Jane", data.getFirstName());
        assertEquals("jane.doe@example.org", data.getEmail());
        assertEquals("1970-01-02", data.getDateOfBirth().toString());
        verify(this.subscriptionRepository).findById((Long) any());
        verify(this.subscriptionRepository).save((Subscription) any());
    }

    @Test
    void testCancelSubscription2() {
        Subscription subscription = new Subscription();
        subscription.setNewsLetterId("42");
        subscription.setEmail("jane.doe@example.org");
        subscription.setDateOfBirth(LocalDate.ofEpochDay(1L));
        subscription.setId(123L);
        subscription.setState(SubscriptionState.CANCELED);
        subscription.setGender(Gender.MALE);
        subscription.setFirstName("Jane");
        subscription.setConsent(true);
        when(this.subscriptionRepository.save((Subscription) any())).thenReturn(subscription);
        when(this.subscriptionRepository.findById((Long) any())).thenReturn(Optional.<Subscription>empty());
        assertEquals("Subscription not found", this.subscriptionServiceImpl.cancelSubscription(123L).getMessage());
        verify(this.subscriptionRepository).findById((Long) any());
    }

    @Test
    void testGetSubscription() {
        Subscription subscription = new Subscription();
        subscription.setNewsLetterId("42");
        subscription.setEmail("jane.doe@example.org");
        subscription.setDateOfBirth(LocalDate.ofEpochDay(1L));
        subscription.setId(123L);
        subscription.setState(SubscriptionState.CANCELED);
        subscription.setGender(Gender.MALE);
        subscription.setFirstName("Jane");
        subscription.setConsent(true);
        Optional<Subscription> ofResult = Optional.<Subscription>of(subscription);
        when(this.subscriptionRepository.findById((Long) any())).thenReturn(ofResult);
        ServiceResponse<SubscriptionResponse> actualSubscription = this.subscriptionServiceImpl.getSubscription(123L);
        assertEquals("ServiceResponse(message=Successful, data=SubscriptionResponse(id=123, email=jane.doe@example.org,"
                        + " firstName=Jane, gender=MALE, dateOfBirth=1970-01-02, consent=true, newsLetterId=42, state=CANCELED)" + ")",
                actualSubscription.toString());
        assertEquals("Successful", actualSubscription.getMessage());
        SubscriptionResponse data = actualSubscription.getData();
        assertTrue(data.isConsent());
        assertEquals(SubscriptionState.CANCELED, data.getState());
        assertEquals("42", data.getNewsLetterId());
        assertEquals(123L, data.getId().longValue());
        assertEquals(Gender.MALE, data.getGender());
        assertEquals("Jane", data.getFirstName());
        assertEquals("jane.doe@example.org", data.getEmail());
        assertEquals("1970-01-02", data.getDateOfBirth().toString());
        verify(this.subscriptionRepository).findById((Long) any());
    }

    @Test
    void testGetSubscription2() {
        when(this.subscriptionRepository.findById((Long) any())).thenReturn(Optional.<Subscription>empty());
        assertEquals("Subscription not found", this.subscriptionServiceImpl.getSubscription(123L).getMessage());
        verify(this.subscriptionRepository).findById((Long) any());
    }

    @Test
    void testListAllSubscriptions() {
        ArrayList<Subscription> subscriptionList = new ArrayList<Subscription>();
        when(this.subscriptionRepository.findAll()).thenReturn(subscriptionList);
        ServiceResponse<List<SubscriptionResponse>> actualListAllSubscriptionsResult = this.subscriptionServiceImpl
                .listAllSubscriptions();
        assertEquals(subscriptionList, actualListAllSubscriptionsResult.getData());
        assertEquals("Successful", actualListAllSubscriptionsResult.getMessage());
        verify(this.subscriptionRepository).findAll();
    }

    @Test
    void testListAllSubscriptions2() {
        Subscription subscription = new Subscription();
        subscription.setNewsLetterId("42");
        subscription.setEmail("jane.doe@example.org");
        subscription.setDateOfBirth(LocalDate.ofEpochDay(1L));
        subscription.setId(123L);
        subscription.setState(SubscriptionState.CANCELED);
        subscription.setGender(Gender.MALE);
        subscription.setFirstName("Jane");
        subscription.setConsent(true);

        ArrayList<Subscription> subscriptionList = new ArrayList<Subscription>();
        subscriptionList.add(subscription);
        when(this.subscriptionRepository.findAll()).thenReturn(subscriptionList);
        ServiceResponse<List<SubscriptionResponse>> actualListAllSubscriptionsResult = this.subscriptionServiceImpl
                .listAllSubscriptions();
        assertEquals(1, actualListAllSubscriptionsResult.getData().size());
        assertEquals("ServiceResponse(message=Successful, data=[SubscriptionResponse(id=123, email=jane.doe@example.org,"
                        + " firstName=Jane, gender=MALE, dateOfBirth=1970-01-02, consent=true, newsLetterId=42, state=CANCELED" + ")])",
                actualListAllSubscriptionsResult.toString());
        assertEquals("Successful", actualListAllSubscriptionsResult.getMessage());
        verify(this.subscriptionRepository).findAll();
    }

    @Test
    void testListAllSubscriptions3() {
        Subscription subscription = new Subscription();
        subscription.setNewsLetterId("42");
        subscription.setEmail("jane.doe@example.org");
        subscription.setDateOfBirth(LocalDate.ofEpochDay(1L));
        subscription.setId(123L);
        subscription.setState(SubscriptionState.CANCELED);
        subscription.setGender(Gender.MALE);
        subscription.setFirstName("Jane");
        subscription.setConsent(true);

        Subscription subscription1 = new Subscription();
        subscription1.setNewsLetterId("42");
        subscription1.setEmail("jane.doe@example.org");
        subscription1.setDateOfBirth(LocalDate.ofEpochDay(1L));
        subscription1.setId(123L);
        subscription1.setState(SubscriptionState.CANCELED);
        subscription1.setGender(Gender.MALE);
        subscription1.setFirstName("Jane");
        subscription1.setConsent(true);

        ArrayList<Subscription> subscriptionList = new ArrayList<Subscription>();
        subscriptionList.add(subscription1);
        subscriptionList.add(subscription);
        when(this.subscriptionRepository.findAll()).thenReturn(subscriptionList);
        ServiceResponse<List<SubscriptionResponse>> actualListAllSubscriptionsResult = this.subscriptionServiceImpl
                .listAllSubscriptions();
        assertEquals(2, actualListAllSubscriptionsResult.getData().size());
        assertEquals(
                "ServiceResponse(message=Successful, data=[SubscriptionResponse(id=123, email=jane.doe@example.org,"
                        + " firstName=Jane, gender=MALE, dateOfBirth=1970-01-02, consent=true, newsLetterId=42, state=CANCELED),"
                        + " SubscriptionResponse(id=123, email=jane.doe@example.org, firstName=Jane, gender=MALE, dateOfBirth"
                        + "=1970-01-02, consent=true, newsLetterId=42, state=CANCELED)])",
                actualListAllSubscriptionsResult.toString());
        assertEquals("Successful", actualListAllSubscriptionsResult.getMessage());
        verify(this.subscriptionRepository).findAll();
    }
}

