package com.notifications.java.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notifications.java.Dto.BorrowDto;
import com.notifications.java.models.Borrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.io.IOException;

public class Listener {

    @Autowired
    BorrowService borrowService;

    @Autowired
    MailSender mailSender;

    @JmsListener(destination = "testQueue")
    public void receive(Message message)
    {
        if (message instanceof TextMessage) {// we set the converter targetType to text
            try {
                String json = ((TextMessage) message).getText();
                BorrowDto borrowDto = ReadValue(json, BorrowDto.class);
                Borrow borrow = borrowService.ConvertFromDto(borrowDto);
                mailSender.send(borrow);
            }
            catch (JMSException ex) {
                throw new RuntimeException(ex);
            }
        }
        else {
            throw new IllegalArgumentException("Message must be of type TextMessage");
        }
    }

    public <T> T ReadValue(String str, Class<T> valueType){
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
