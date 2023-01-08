package com.samhcoco.projects.spring.rabbitmq.banking.core.repository;

import com.samhcoco.projects.spring.rabbitmq.banking.core.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
}
