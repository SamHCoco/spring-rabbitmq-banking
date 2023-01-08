package com.samhcoco.projects.spring.rabbitmq.banking.debit.listener;


import com.samhcoco.projects.spring.rabbitmq.banking.core.model.DebitTransaction;
import com.samhcoco.projects.spring.rabbitmq.banking.debit.service.DebitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DebitTransactionListener {

    private final DebitService debitService;

    @RabbitListener(queues = "debit-transaction-queue")
    public void debitTransactionReceived(DebitTransaction transaction) {
        log.info("DEBIT transaction pulled from RabbitMQ queue: {}", transaction);
        debitService.debitAccount(transaction);
    }

}
