package com.samhcoco.projects.spring.rabbitmq.banking.debit.service;

import com.samhcoco.projects.spring.rabbitmq.banking.core.model.DebitTransaction;

public interface DebitService {

    DebitTransaction debitAccount(DebitTransaction transaction);

}
