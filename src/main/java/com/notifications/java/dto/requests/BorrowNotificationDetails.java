package com.notifications.java.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;


@Value
@AllArgsConstructor
@Builder(toBuilder = true)
public class BorrowNotificationDetails {
    @NotBlank
    private final String holderFirstName;
    @NotBlank
    private final String holderLastName;
    @NotBlank
    private final String holderEmail;
    @NotEmpty
    private final List<String> books;
    private final long expiredDate;
}
