package com.notifications.java.services;

import com.notifications.java.config.EmailConfig;
import com.notifications.java.models.Borrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

import static java.util.stream.Collectors.joining;

@Service
public class MailSender {

    @Autowired
    EmailConfig emailConfig;

    public void send(Borrow borrow){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(emailConfig.getHost());
        mailSender.setPort(emailConfig.getPort());
        mailSender.setUsername(emailConfig.getUsername());
        mailSender.setPassword(emailConfig.getPassword());

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(emailConfig.getUsername());
        mailMessage.setTo(borrow.getHolder().getEmail());
        mailMessage.setSubject("Borrow have already expired");
        String holder = String.format("%s %s",borrow.getHolder().getFirstName(),borrow.getHolder().getLastName());
        String books = borrow.getBooks().stream().map(s -> s.getTitle()).collect(joining(", "));
        String text = String.format("Dear %s,\n\nTime of your borrow have already expired\nyou need return next books:\n%s\n\nBest regards, \nBook Library Team", holder, books);
        mailMessage.setText(text);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        mailSender.send(mailMessage);

    }

}
