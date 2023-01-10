package com.samhcoco.projects.spring.rabbitmq.banking.core.repository;

        import com.samhcoco.projects.spring.rabbitmq.banking.core.model.CreditTransaction;
        import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditTransactionRepository extends JpaRepository<CreditTransaction, Integer> {
}
