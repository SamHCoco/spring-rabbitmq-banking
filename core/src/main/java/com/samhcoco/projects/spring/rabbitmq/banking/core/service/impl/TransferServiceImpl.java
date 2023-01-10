package com.samhcoco.projects.spring.rabbitmq.banking.core.service.impl;

import com.samhcoco.projects.spring.rabbitmq.banking.core.model.TransferTransaction;
import com.samhcoco.projects.spring.rabbitmq.banking.core.repository.AccountRepository;
import com.samhcoco.projects.spring.rabbitmq.banking.core.repository.TransferTransactionRepository;
import com.samhcoco.projects.spring.rabbitmq.banking.core.service.TransferService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.lang.String.format;
import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final AccountRepository accountRepository;
    private final TransferTransactionRepository transferTransactionRepository;

    @Override
    @Transactional
    public TransferTransaction transfer(@NonNull TransferTransaction transaction) {
        val persisted  = transferTransactionRepository.save(transaction);
        log.info("Successfully persisted transfer to DB using ID '{}': {}", persisted.getId(), persisted);

        val accounts = accountRepository.findAllById(Set.of(persisted.getAccountId(), persisted.getReceiverAccountId()));

        var account = accounts.stream()
                              .filter(a -> a.getId() == persisted.getAccountId())
                              .findFirst()
                              .orElse(null);

        var receivingAccount = accounts.stream()
                                       .filter(a -> a.getId() == persisted.getReceiverAccountId())
                                       .findFirst()
                                       .orElse(null);

        if (isNull(account) || isNull(receivingAccount)) {
            log.info("TRANSFER failed: Could not find required Accounts IDs for transfer with ID {}", persisted.getId());
            return null;
        }

        account.setBalance(account.getBalance().subtract(persisted.getAmount()));
        receivingAccount.setBalance(receivingAccount.getBalance().add(persisted.getAmount()));

        accountRepository.saveAll(List.of(account, receivingAccount));

        log.info("TRANSFER - successfully transferred £'{}' from Account ID '{}' to Account ID '{}'.",
                 persisted.getAmount(), account.getId(), receivingAccount.getId());

        return persisted;
    }

    @Override
    public Map<String, String> validate(@NonNull TransferTransaction transaction) {
        val failures = new HashMap<String, String>();

        val account = accountRepository.findById(transaction.getAccountId());
        if (isNull(account)) {
            failures.put("accountId", "Account ID does not exist.");
        }

        val receivingAccountId = transaction.getReceiverAccountId();
        if (!accountRepository.existsById(receivingAccountId)) {
            failures.put("receivingAccountId", "ID does not exist.");
        }

        val amount = transaction.getAmount();
        if (isNull(amount)) {
            failures.put("amount", "Transfer amount null.");

        } else if (amount.compareTo(BigDecimal.ZERO) < 0) {
            failures.put("amount", "Transfer amount must be positive value.");

        } else if (!(account.getBalance().compareTo(amount) > 0)) {
            failures.put("amount", format("Transfer not possible. Account has insufficient funds: £'%s'",
                                          account.getBalance()));
        }
        return failures;
    }
}
