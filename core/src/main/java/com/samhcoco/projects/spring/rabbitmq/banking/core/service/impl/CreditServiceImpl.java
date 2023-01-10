package com.samhcoco.projects.spring.rabbitmq.banking.core.service.impl;

import com.samhcoco.projects.spring.rabbitmq.banking.core.model.CreditTransaction;
import com.samhcoco.projects.spring.rabbitmq.banking.core.repository.AccountRepository;
import com.samhcoco.projects.spring.rabbitmq.banking.core.repository.CreditTransactionRepository;
import com.samhcoco.projects.spring.rabbitmq.banking.core.service.CreditService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreditServiceImpl implements CreditService {

    private final AccountRepository accountRepository;
    private final CreditTransactionRepository creditTransactionRepository;

    @Override
    @Transactional
    public CreditTransaction creditAccount(@NonNull CreditTransaction transaction) {
        val failures = validate(transaction);
        if (!failures.isEmpty()) {
            log.error("DEBIT {} failed - reasons: {}", transaction, failures);
            return null;
        }

        // todo - add api call to fetch exchange rage
        transaction.setExchangeRate(new BigDecimal("1.00"));
        creditTransactionRepository.save(transaction);

        val account = accountRepository.findById(transaction.getAccountId());
        if (isNull(account)) {
            log.error("DEBIT {} failed: No account with ID {} found.", transaction, transaction.getAccountId());
            return null;
        }

        val now = new Date();
        account.setBalance(account.getBalance().add(transaction.getAmount()));
        account.setModified(now);
        accountRepository.save(account);
        log.info("DEBIT {} success: {}", transaction, account);
        return transaction;
    }

    public Map<String, String> validate(@NonNull CreditTransaction transaction) {
        val failures = new HashMap<String, String>();

        val id = transaction.getId();
        if (creditTransactionRepository.existsById(id) && id != 0) {
            failures.put("id", String.format("Debit transaction with ID '%s' already exists.", transaction.getId()));
        }

        val amount = transaction.getAmount();
        if (isNull(amount)) {
            failures.put("amount", "Debit amount cannot be null.");
        } else if (amount.compareTo(BigDecimal.ZERO) < 0) {
            failures.put("amount", "Debit amount must be greater than 0.");
        }

        return failures;
    }
}
