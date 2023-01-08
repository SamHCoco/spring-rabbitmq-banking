package com.samhcoco.projects.spring.rabbitmq.banking.core.service;

import com.samhcoco.projects.spring.rabbitmq.banking.core.model.Account;
import com.samhcoco.projects.spring.rabbitmq.banking.core.model.TransactionDto;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface AccountService {

    TransactionDto debit(TransactionDto transaction);

    TransactionDto transfer(TransactionDto transaction);

    Map<String, String> validate(int accountId, TransactionDto transaction);

    List<Account> findAccountByIds(Set<Integer> ids);

}
