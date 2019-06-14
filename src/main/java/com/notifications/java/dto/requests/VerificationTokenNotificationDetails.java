package com.notifications.java.dto.requests;


import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class VerificationTokenNotificationDetails {
    private final String verificationToken;
    private final String username;
    private final String userEmail;
}
