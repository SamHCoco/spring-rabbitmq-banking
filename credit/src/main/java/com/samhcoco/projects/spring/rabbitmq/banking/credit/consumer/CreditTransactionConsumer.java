package com.samhcoco.projects.spring.rabbitmq.banking.credit.consumer;


import com.samhcoco.projects.spring.rabbitmq.banking.core.model.CreditTransaction;
import com.samhcoco.projects.spring.rabbitmq.banking.core.service.CreditService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreditTransactionConsumer {

    private final CreditService creditService;

    @RabbitListener(queues = "credit-transaction-queue")
    public void creditTransactionConsumer(@NonNull CreditTransaction transaction) {
        log.info("CREDIT transaction consumed from RabbitMQ queue: {}", transaction);
        creditService.creditAccount(transaction);
    }

}
