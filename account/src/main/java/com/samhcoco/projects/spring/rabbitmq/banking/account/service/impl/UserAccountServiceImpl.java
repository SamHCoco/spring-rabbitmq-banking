package com.samhcoco.projects.spring.rabbitmq.banking.account.service.impl;

import com.samhcoco.projects.spring.rabbitmq.banking.account.model.UserAccount;
import com.samhcoco.projects.spring.rabbitmq.banking.account.service.UserAccountService;
import com.samhcoco.projects.spring.rabbitmq.banking.core.model.User;
import com.samhcoco.projects.spring.rabbitmq.banking.core.repository.AccountRepository;
import com.samhcoco.projects.spring.rabbitmq.banking.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    @Override
    public List<UserAccount> listAll() {
        val accounts = accountRepository.findAll();

        val users = userRepository.findAll().stream()
                                  .collect(toMap(User::getId, Function.identity()));

        val userAccounts = new ArrayList<UserAccount>();

        accounts.forEach(a -> {
            userAccounts.add(UserAccount.builder()
                                        .account(a)
                                        .user(users.get(a.getUserId()))
                                        .build());
        });

        return userAccounts;
    }
}
