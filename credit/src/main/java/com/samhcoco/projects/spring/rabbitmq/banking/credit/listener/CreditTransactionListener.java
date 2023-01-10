package com.samhcoco.projects.spring.rabbitmq.banking.credit.listener;


import com.samhcoco.projects.spring.rabbitmq.banking.core.model.CreditTransaction;
import com.samhcoco.projects.spring.rabbitmq.banking.credit.service.CreditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreditTransactionListener {

    private final CreditService creditService;

    @RabbitListener(queues = "credit-transaction-queue")
    public void debitTransactionReceived(CreditTransaction transaction) {
        log.info("CREDIT transaction pulled from RabbitMQ queue: {}", transaction);
        creditService.creditAccount(transaction);
    }

}
