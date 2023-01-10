package com.samhcoco.projects.spring.rabbitmq.banking.core.repository;

import com.samhcoco.projects.spring.rabbitmq.banking.core.model.TransferTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferTransactionRepository extends JpaRepository<TransferTransaction, Integer> {
}
