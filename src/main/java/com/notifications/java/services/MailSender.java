package com.notifications.java.services;

import com.notifications.java.config.EmailConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class MailSender {

    @Autowired
    EmailConfig emailConfig;

    public void send(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(emailConfig.getHost());
        mailSender.setPort(emailConfig.getPort());
        mailSender.setUsername(emailConfig.getUsername());
        mailSender.setPassword(emailConfig.getPassword());

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(emailConfig.getUsername());
        mailMessage.setTo("anamartynova87@gmail.com");
        mailMessage.setSubject("Borrow have already expired");
        String text = String.format("Dear %s,\n\nTime of your borrow have already expired\nyou need return next books:\n%s\n\nBest regards, \nBook Library Team", "holder","book1");
        mailMessage.setText(text);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        mailSender.send(mailMessage);

    }

}
