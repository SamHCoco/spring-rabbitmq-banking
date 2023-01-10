package com.samhcoco.projects.spring.rabbitmq.banking.account.controller;

import com.samhcoco.projects.spring.rabbitmq.banking.core.model.CreditTransaction;
import com.samhcoco.projects.spring.rabbitmq.banking.core.model.TransferTransaction;
import com.samhcoco.projects.spring.rabbitmq.banking.core.service.AccountService;
import com.samhcoco.projects.spring.rabbitmq.banking.core.service.CreditService;
import com.samhcoco.projects.spring.rabbitmq.banking.core.service.TransferService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static java.lang.String.format;
import static java.util.Objects.isNull;
import static org.springframework.http.HttpStatus.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/account")
public class AccountController {

    private final AccountService accountService;
    private final CreditService creditService;
    private final TransferService transferService;

    @PostMapping("{accountId}/credit")
    public ResponseEntity<Object> credit(@PathVariable Integer accountId,
                                         @RequestBody CreditTransaction transaction) {
        val failures = accountService.validate(accountId, transaction);
        failures.putAll(creditService.validate(transaction));


        if (!failures.isEmpty()) {
            return new ResponseEntity<>(failures, BAD_REQUEST);
        }

        try {
            val now = new Date();
            transaction.setCreated(now);
            transaction.setModified(now);
            return new ResponseEntity<>(accountService.credit(transaction), OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("{accountId}/transfer")
    public ResponseEntity<Object> transfer(@PathVariable Integer accountId,
                                           @RequestBody TransferTransaction transaction) {
        val failures = accountService.validate(accountId, transaction);
        failures.putAll(transferService.validate(transaction));

        if (!failures.isEmpty()) {
            return new ResponseEntity<>(failures, BAD_REQUEST);
        }

        try {
            val now = new Date();
            transaction.setCreated(now);
            transaction.setModified(now);
            return new ResponseEntity<>(accountService.transfer(transaction), OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<Object> getAccountByUserId(@PathVariable Integer userId) {
        val account = accountService.getAccountByUserId(userId);
        if (isNull(account)) {
            return new ResponseEntity<>(format("No Account with user ID '%s' exists.", userId), NOT_FOUND);
        }
        return new ResponseEntity<>(account, OK);
    }

}
