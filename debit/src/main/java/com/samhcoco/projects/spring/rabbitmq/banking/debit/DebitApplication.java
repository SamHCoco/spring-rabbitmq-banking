package com.samhcoco.projects.spring.rabbitmq.banking.debit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.samhcoco")
public class DebitApplication {

    public static void main(String[] args) {
        SpringApplication.run(DebitApplication.class, args);
    }

}
