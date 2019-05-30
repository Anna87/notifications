package com.notifications.java.models;

import lombok.*;

import java.util.Date;

@Builder(toBuilder = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VerificationToken {
    private String id;
    private String token;
    private User user;
    private Date expiryDate;
    private TokenVerivicationStatus status;
}
