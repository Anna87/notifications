package com.notifications.java.config;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class EmailConfig {

    @Value("${email.config.host:}")
    private String host;

    @Value("${email.config.port:}")
    private int port;

    @Value("${email.config.username:}")
    private String username;

    @Value("${email.config.password:}")
    private String password;

    @Value("${email.config.siteUrl:}")
    private String siteUrl;

}
