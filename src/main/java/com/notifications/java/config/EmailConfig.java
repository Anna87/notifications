package com.notifications.java.config;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class EmailConfig {

    @Value("smtp.gmail.com")
    private String host;

    @Value("587")
    private int port;

    @Value("anamartynova87@gmail.com")
    private String username;

    @Value("Admin123$%")
    private String password;

    @Value("http://localhost:3000")
    private String siteUrl;

}
