package com.samhcoco.projects.spring.rabbitmq.banking.core.service.impl;

import com.samhcoco.projects.spring.rabbitmq.banking.core.repository.AccountRepository;
import com.samhcoco.projects.spring.rabbitmq.banking.core.service.AccountService;
import com.samhcoco.projects.spring.rabbitmq.banking.core.model.Account;
import com.samhcoco.projects.spring.rabbitmq.banking.core.model.TransactionDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    @Value("${queue.debit-transaction}")
    private String debitTransactionQueue;

    @Value("${queue.transfer-transaction}")
    private String transferTransactionQueue;

    @Value("${exchange.banking}")
    private String bankingExchange;

    private final RabbitTemplate rabbitTemplate;
    private final AccountRepository accountRepository;

    @Override
    public TransactionDto debit(@NonNull TransactionDto transaction) {
        rabbitTemplate.convertAndSend(bankingExchange, debitTransactionQueue, transaction);
        log.info("DEBIT transaction for account ID {} queued via RabbitMQ.", transaction.getAccountId());
        return transaction;
    }

    @Override
    public TransactionDto transfer(@NonNull TransactionDto transaction) {
        rabbitTemplate.convertAndSend(bankingExchange, transferTransactionQueue, transaction);

        log.info("TRANSFER transaction from account ID {} to {} queued via RabbitMQ",
                transaction.getAccountId(), transaction.getReceivingAccountId());

        return transaction;
    }

    @Override
    public List<Account> findAccountByIds(@NonNull Set<Integer> ids) {
        return accountRepository.findByIdIn(ids);
    }

    @Override
    public Map<String, String> validate(@NonNull int accountId, @NonNull TransactionDto transaction) {
        val failures = new HashMap<String, String>();
        if (accountId != transaction.getAccountId()) {
            failures.put("accountId", "Transaction not associated with correct Account ID.");
        }
        return failures;
    }
}