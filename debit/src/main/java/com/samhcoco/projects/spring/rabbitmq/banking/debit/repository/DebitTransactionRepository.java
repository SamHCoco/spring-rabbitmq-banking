package com.samhcoco.projects.spring.rabbitmq.banking.debit.repository;

        import com.samhcoco.projects.spring.rabbitmq.banking.core.model.DebitTransaction;
        import org.springframework.data.jpa.repository.JpaRepository;

public interface DebitTransactionRepository extends JpaRepository<DebitTransaction, Integer> {
}
