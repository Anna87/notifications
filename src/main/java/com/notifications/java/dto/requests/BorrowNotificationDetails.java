package com.notifications.java.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowNotificationDetails {
    String holderFullName;
    String holderEmail;
    List<String> books;
    long expiredDate;
}
