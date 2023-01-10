package com.samhcoco.projects.spring.rabbitmq.banking.core.model;

import lombok.Data;

@Data
public class UserAccount {
    private User user;
    private Account account;
}
