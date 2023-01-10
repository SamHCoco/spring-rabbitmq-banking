package com.samhcoco.projects.spring.rabbitmq.banking.credit.service;

import com.samhcoco.projects.spring.rabbitmq.banking.core.model.Account;
import com.samhcoco.projects.spring.rabbitmq.banking.core.model.CreditTransaction;
import com.samhcoco.projects.spring.rabbitmq.banking.core.model.Transaction;

public interface CreditService {

    /**
     * Credits a {@link Account} with the given {@link Transaction}
     * @param transaction {@link Transaction}.
     * @return The debited {@link Transaction}.
     */
    CreditTransaction creditAccount(CreditTransaction transaction);

}
