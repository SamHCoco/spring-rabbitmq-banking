package com.samhcoco.projects.spring.rabbitmq.banking.account.service;

import com.samhcoco.projects.spring.rabbitmq.banking.account.model.UserAccount;

import java.util.List;

public interface UserAccountService {

    /**
     * Returns all {@link UserAccount}.
     * @return All {@link UserAccount}.
     */
    List<UserAccount> listAll();

}
