package com.notifications.java.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notifications.java.dto.requests.BorrowNotificationDetails;
import com.notifications.java.models.VerificationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.io.IOException;

@RequiredArgsConstructor
@Service
public class Listener {

    private final MailSender mailSender;

    @JmsListener(destination = "library-queue")
    public void receiveBorrow(final Message message)
    {
        if (message instanceof TextMessage) {// we set the converter targetType to text
            try {
                String json = ((TextMessage) message).getText();
                BorrowNotificationDetails borrowNotificationDetails = readValue(json, BorrowNotificationDetails.class);
                mailSender.sendExpirationNotification(borrowNotificationDetails);
            }
            catch (JMSException ex) {
                throw new RuntimeException(ex);
            }
        }
        else {
            throw new IllegalArgumentException("Message must be of type TextMessage");
        }
    }

    @JmsListener(destination = "verificationToken-queue")
    public void receiveVerificationToken(Message message)
    {
        if (message instanceof TextMessage) {
            try {
                String json = ((TextMessage) message).getText();
                VerificationToken verificationToken = readValue(json, VerificationToken.class);
                mailSender.sendVeificationToken(verificationToken);
            }
            catch (JMSException ex) {
                throw new RuntimeException(ex);
            }
        }
        else {
            throw new IllegalArgumentException("Message must be of type TextMessage");
        }
    }

    public <T> T readValue(String str, Class<T> valueType){
        ObjectMapper objectMapper = new ObjectMapper();
        T obj = null;
        try {
            obj = objectMapper.readValue(str, valueType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }

}
