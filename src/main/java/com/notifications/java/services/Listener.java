package com.notifications.java.services;

import com.notifications.java.dto.requests.NotificationDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RequiredArgsConstructor
@Service
@Slf4j
public class Listener {

    private final MailSender mailSender;

    @JmsListener(destination = "notification-queue")
    public void receiveBorrowNotificationDetails(NotificationDetails notificationDetails) throws IOException {
        mailSender.sendNotification(notificationDetails);
    }

}
