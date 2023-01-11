package com.samhcoco.projects.spring.rabbitmq.banking.account.model;

import com.samhcoco.projects.spring.rabbitmq.banking.core.model.Account;
import com.samhcoco.projects.spring.rabbitmq.banking.core.model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserAccount {
    private User user;
    private Account account;
}
