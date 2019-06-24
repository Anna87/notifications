package com.notifications.java.utils;

import com.notifications.java.config.EmailConfig;
import com.notifications.java.dto.requests.NotificationDetails;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@UtilityClass
public class MessageUtils {

    @Autowired
    private EmailConfig emailConfig;

    @Autowired
    private JavaMailSender javaMailSender;;

    public static MimeMessage prepareMimeMessage(NotificationDetails notificationDetails, String content) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setSubject(notificationDetails.getSubject());
        mimeMessageHelper.setFrom(emailConfig.getUsername());
        mimeMessageHelper.setTo(notificationDetails.getRecipient());
        mimeMessageHelper.setText(content, true);
        return  mimeMessageHelper.getMimeMessage();
    }
}
