package com.samhcoco.projects.spring.rabbitmq.banking.debit.service.impl;

import com.samhcoco.projects.spring.rabbitmq.banking.core.model.DebitTransaction;
import com.samhcoco.projects.spring.rabbitmq.banking.core.model.Transaction;
import com.samhcoco.projects.spring.rabbitmq.banking.core.repository.AccountRepository;
import com.samhcoco.projects.spring.rabbitmq.banking.debit.repository.DebitTransactionRepository;
import com.samhcoco.projects.spring.rabbitmq.banking.debit.service.DebitService;
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
public class DebitServiceImpl implements DebitService {

    private final AccountRepository accountRepository;
    private final DebitTransactionRepository debitTransactionRepository;

    @Override
    @Transactional
    public DebitTransaction debitAccount(@NonNull DebitTransaction transaction) {
        val failures = validate(transaction);
        if (!failures.isEmpty()) {
            log.error("DEBIT {} failed - reasons: {}", transaction, failures);
            return null;
        }

        val account = accountRepository.findById(transaction.getAccountId());
        if (isNull(account)) {
            log.error("DEBIT {} failed: No account with ID {} found.", transaction, transaction.getAccountId());
            return null;
        }

        debitTransactionRepository.save(transaction);

        val now = new Date();
        account.setBalance(account.getBalance().add(transaction.getAmount()));
        account.setLastModified(now);
        accountRepository.save(account);
        log.info("DEBIT {} success: {}", transaction, account);
        return transaction;
    }

    /**
     * Validates that a debit operation can be completed with the given {@link Transaction}.
     * @param transaction {@link Transaction}.
     * @return Failures reasons, or empty if validation passed.
     */
    private Map<String, String> validate(@NonNull Transaction transaction) {
        val failures = new HashMap<String, String>();

        val id = transaction.getId();
        if (debitTransactionRepository.existsById(id) && id != 0) {
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
