package com.notifications.java;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.notifications.java"})
public class Application {

    public static void main(String[] arg) {
        SpringApplication.run(Application.class);
    }
}
