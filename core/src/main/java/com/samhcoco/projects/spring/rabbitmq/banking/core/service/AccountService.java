package com.samhcoco.projects.spring.rabbitmq.banking.core.service;

import com.samhcoco.projects.spring.rabbitmq.banking.core.model.Account;
import com.samhcoco.projects.spring.rabbitmq.banking.core.model.User;
import com.samhcoco.projects.spring.rabbitmq.banking.core.model.TransactionDto;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface AccountService {

    /**
     * Credits a {@link User} account with the given {@link TransactionDto}.
     * @param transaction {@link TransactionDto}.
     * @return Credited {@link TransactionDto}.
     */
    TransactionDto credit(TransactionDto transaction);

    /**
     * Transfers the given {@link TransactionDto} amount to another {@link Account}.
     * @param transaction {@link TransactionDto}.
     * @return Transfer {@link TransactionDto}.
     */
    TransactionDto transfer(TransactionDto transaction);

    /**
     * Validates the given {@link TransactionDto} against the supplied {@link Account} ID.
     * @param accountId {@link Account} ID.
     * @param transaction {@link TransactionDto}.
     * @return Failure reasons if validation failed, or empty if passed.
     */
    Map<String, String> validate(int accountId, TransactionDto transaction);

    /**
     * Finds and returns {@link Account} with the given {@link Account} IDs.
     * @param ids {@link Account} IDs.
     * @return {@link Account}s.
     */
    List<Account> findAccountByIds(Set<Integer> ids);

    /**
     * Returns the {@link Account} associated with the given {@link User} ID.
     * @param userId {@link User} ID.
     * @return {@link Account} or <code>null</code> if none found.
     */
    Account getAccountByUserId(Integer userId);

}
