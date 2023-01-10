package com.samhcoco.projects.spring.rabbitmq.banking.core.service;

import com.samhcoco.projects.spring.rabbitmq.banking.core.model.Account;
import com.samhcoco.projects.spring.rabbitmq.banking.core.model.CreditTransaction;
import com.samhcoco.projects.spring.rabbitmq.banking.core.model.Transaction;

import java.util.Map;

public interface CreditService {

    /**
     * Credits a {@link Account} with the given {@link Transaction}
     * @param transaction {@link Transaction}.
     * @return The debited {@link Transaction}.
     */
    CreditTransaction creditAccount(CreditTransaction transaction);

    /**
     * Validates that a debit operation can be completed with the given {@link Transaction}.
     * @param transaction {@link Transaction}.
     * @return Failures reasons, or empty if validation passed.
     */
    Map<String, String> validate(CreditTransaction transaction);

}
