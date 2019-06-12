package com.notifications.java.services;

import com.notifications.java.config.EmailConfig;
import com.notifications.java.dto.requests.BorrowNotificationDetails;
import com.notifications.java.models.VerificationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

import static java.util.stream.Collectors.joining;

@RequiredArgsConstructor
@Service
public class MailSender {

    private final EmailConfig emailConfig;

    public void sendExpirationNotification(final BorrowNotificationDetails borrowNotificationDetails){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(emailConfig.getHost());
        mailSender.setPort(emailConfig.getPort());
        mailSender.setUsername(emailConfig.getUsername());
        mailSender.setPassword(emailConfig.getPassword());

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(emailConfig.getUsername());
        mailMessage.setTo(borrowNotificationDetails.getHolderEmail());
        mailMessage.setSubject("Borrow have already expired");
        String books = borrowNotificationDetails.getBooks().stream().collect(joining(", "));
        String text = String.format("Dear %s,\n\nTime of your borrow have already expired\nyou need return next books:\n%s\n\nBest regards, \nBook Library Team",
                borrowNotificationDetails.getHolderFullName(),
                books);
        mailMessage.setText(text);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        mailSender.send(mailMessage);
    }

    public void sendVeificationToken(VerificationToken verificationToken) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(emailConfig.getHost());
        mailSender.setPort(emailConfig.getPort());
        mailSender.setUsername(emailConfig.getUsername());
        mailSender.setPassword(emailConfig.getPassword());

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(emailConfig.getUsername());
        mailMessage.setTo(verificationToken.getUser().getEmail());
        mailMessage.setSubject("Registration Confirmation");
        String confirmationUrl = emailConfig.getSiteUrl() + "/activate?token=" + verificationToken.getToken();
        String message = "Dear "+verificationToken.getUser().getUsername()+" ,\n\nPlease confirm your e-mail with this link. ";
        mailMessage.setText(message + "\n\n" + confirmationUrl + "\n\n\nBest regards \nSupport Team");
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        mailSender.send(mailMessage);
    }

}
