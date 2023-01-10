package com.samhcoco.projects.spring.rabbitmq.banking.transfer.consumer;

import com.samhcoco.projects.spring.rabbitmq.banking.core.model.TransferTransaction;
import com.samhcoco.projects.spring.rabbitmq.banking.core.service.TransferService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferTransactionConsumer {

    private final TransferService transferService;

    @RabbitListener(queues = "transfer-transaction-queue")
    public void transferTransactionConsumer(@NonNull TransferTransaction transaction) {
        log.info("TRANSFER transaction consumed from RabbitMQ queue: {}", transaction);
        transferService.transfer(transaction);
    }

}
