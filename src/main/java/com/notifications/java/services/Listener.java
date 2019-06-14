package com.notifications.java.services;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.notifications.java.dto.requests.BorrowNotificationDetails;
import com.notifications.java.dto.requests.VerificationTokenNotificationDetails;
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
    public void receiveBorrowNotificationDetails(final Message message)
    {
        if (message instanceof TextMessage) {
            try {
                final String json = ((TextMessage) message).getText();
                final BorrowNotificationDetails borrowNotificationDetails = new ObjectMapper()
                        .readValue(json, BorrowNotificationDetails.class);
                mailSender.sendExpirationNotification(borrowNotificationDetails);
            }
            catch (JMSException ex) {
                throw new RuntimeException(ex);
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            throw new IllegalArgumentException("Message must be of type TextMessage");
        }
    }
    /*@JmsListener(destination = "library-queue") //TODO not working
    public void receiveBorrowNotificationDetails(final BorrowNotificationDetails borrowNotificationDetails){
        mailSender.sendExpirationNotification(borrowNotificationDetails);
    }*/

    @JmsListener(destination = "verificationToken-queue")
    public void receiveVerificationToken(final Message message)
    {
        if (message instanceof TextMessage) {
            try {
                final String json = ((TextMessage) message).getText();
                final VerificationTokenNotificationDetails verificationTokenNotificationDetails = new ObjectMapper()
                        .readValue(json, VerificationTokenNotificationDetails.class);
                mailSender.sendVeificationToken(verificationTokenNotificationDetails);
            }
            catch (JMSException ex) {
                throw new RuntimeException(ex);
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            throw new IllegalArgumentException("Message must be of type TextMessage");
        }
    }

}
