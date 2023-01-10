package com.samhcoco.projects.spring.rabbitmq.banking.core.repository;

import com.samhcoco.projects.spring.rabbitmq.banking.core.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findById(int id);

    List<Account> findByIdIn(Set<Integer> ids);

    Account findByUserId(int userId);

}
