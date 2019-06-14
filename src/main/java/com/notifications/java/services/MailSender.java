package com.notifications.java.services;

import com.notifications.java.config.EmailConfig;
import com.notifications.java.dto.requests.BorrowNotificationDetails;
import com.notifications.java.dto.requests.VerificationTokenNotificationDetails;
import com.notifications.java.dto.responses.Mail;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
public class MailSender {

    private final EmailConfig emailConfig;
    //private final Configuration freeMarkerConfig; //TODO ?
    //@Autowired
    //private FreeMarkerConfigurationFactoryBean freemarkerConfig;
    private final JavaMailSender javaMailSender;

    public void sendExpirationNotification(final BorrowNotificationDetails borrowNotificationDetails){

        Map<String,Object> templateParam = new HashMap<>();
        templateParam.put("firstName", borrowNotificationDetails.getHolderFirstName());
        templateParam.put("lastName", borrowNotificationDetails.getHolderLastName());
        templateParam.put("books", borrowNotificationDetails.getBooks());
        templateParam.put("expired", new Date(borrowNotificationDetails.getExpiredDate()));

        Mail mail = Mail.builder()
                .mailTo(borrowNotificationDetails.getHolderEmail())
                .mailFrom(emailConfig.getUsername())
                .mailSubject("Borrow have already expired")
                .contentType("text/html")
                .mailContent(getContentFromTemplate(templateParam,"borrowExpiredNotification.ftl"))
                .build();

        sendEmail(mail);
    }

    public void sendVeificationToken(VerificationTokenNotificationDetails verificationTokenNotificationDetails) {
        Map<String,Object> templateParam = new HashMap<>();
        templateParam.put("username", verificationTokenNotificationDetails.getUsername());
        templateParam.put("token", verificationTokenNotificationDetails.getVerificationToken());
        templateParam.put("siteUrl", emailConfig.getSiteUrl());

        Mail mail = Mail.builder()
                .mailTo(verificationTokenNotificationDetails.getUserEmail())
                .mailFrom(emailConfig.getUsername())
                .mailSubject("Book Library: registration confirm")
                .contentType("text/html")
                .mailContent(getContentFromTemplate(templateParam,"verificationTokenNotification.ftl"))
                .build();

        sendEmail(mail);
    }

    private String getContentFromTemplate(Map <String,Object> model,String templateFilename) {
        StringBuffer content = new StringBuffer();
        Configuration freemarkerConfig = new Configuration(Configuration.VERSION_2_3_28);  //TODO ? how to connect with FreemarkerConfig
        //ClassTemplateLoader loader = new ClassTemplateLoader(getClass(), "/com/notifications/resources/templates");

        ClassTemplateLoader loader = new ClassTemplateLoader(getClass(), "templates");
        freemarkerConfig.setTemplateLoader(loader);
        freemarkerConfig.setDefaultEncoding("UTF-8");
        freemarkerConfig.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER); // TODO for what freemarkconf???
        try {
            content.append(FreeMarkerTemplateUtils
                    .processTemplateIntoString(freemarkerConfig.getTemplate("borrowExpiredNotification.ftl"), model));
        } catch (IOException e) { // TODO find this one is it ok?
            throw new IllegalArgumentException("Can not read templateFilename (" + templateFilename
                    + ").", e);
        } catch (TemplateException e) {
            throw new IllegalArgumentException("Can not process Freemarker templateFilename (" + templateFilename
                    + ").", e);
        }
        return content.toString();
    }

    private void sendEmail(Mail mail){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setSubject(mail.getMailSubject());
            mimeMessageHelper.setFrom(mail.getMailFrom());
            mimeMessageHelper.setTo(mail.getMailTo());
            mimeMessageHelper.setText(mail.getMailContent(), true);

            javaMailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            log.error(e.getMessage()); //TODO log ?
        }
    }

}
