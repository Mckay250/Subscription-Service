package com.adidas.subscriptionservice.dto.requests;

import com.adidas.subscriptionservice.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionCreationRequest {

    @NotBlank(message = "email is required")
    private String email;
    private String firstName;
    private Gender gender;
    @NotNull(message = "dateOfBirth is required")
    private LocalDate dateOfBirth;
    private boolean consent;
    @NotBlank(message = "newsLetterId is required")
    private String newsLetterId;
}
