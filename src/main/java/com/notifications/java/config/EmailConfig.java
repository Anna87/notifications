package com.notifications.java.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

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

    public String getHost() {
        return host;
    }
    public int getPort() {
        return port;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

}
