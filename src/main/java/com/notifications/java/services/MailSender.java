package com.notifications.java.services;

import com.notifications.java.dto.requests.NotificationDetails;
import com.notifications.java.utils.MessageUtils;
import com.notifications.java.utils.TemplateUtils;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@RequiredArgsConstructor
@Service
@Slf4j
public class MailSender {

    private final JavaMailSender javaMailSender;

    public void sendNotification(final NotificationDetails notificationDetails) throws IOException {

        try {
            final String content = TemplateUtils.getContentFromTemplate(notificationDetails.getTemplateParam(),notificationDetails.getTemplateName());
            final MimeMessage mimeMessage = MessageUtils.prepareMimeMessage(notificationDetails, content);
            javaMailSender.send(mimeMessage);
        } catch (TemplateException | MessagingException e) { // TODO is that ok?
            e.printStackTrace();
        }

    }
}
