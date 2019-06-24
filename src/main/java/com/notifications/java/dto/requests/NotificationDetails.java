package com.notifications.java.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Map;


@Value
@AllArgsConstructor
@Builder(toBuilder = true)
public class NotificationDetails { // NotificationREquest
    @NotEmpty
    private final Map<String,Object> templateParam;
    @Email
    private final String recipient;
    @NotBlank
    private final String subject;
    @NotBlank
    private final String templateName;
}
