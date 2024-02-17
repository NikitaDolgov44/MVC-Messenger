package com.intellekta.messenger.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.intellekta.messenger.jpa")
public class MessengerApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MessengerApplication.class, args);
        MessageRepository saleRepository = context.getBean(MessageRepository.class);
        saleRepository.deleteAll();
    }
}
