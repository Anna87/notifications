package com.notifications.java.dto.responses;


import lombok.Builder;
import lombok.Value;

@Builder(toBuilder = true)
@Value
public class Mail {
    private final String mailFrom;
    private final String mailTo;
    private final String mailSubject;
    private final String mailContent;
    private final String contentType;
}
