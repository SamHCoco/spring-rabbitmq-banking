package com.samhcoco.projects.spring.rabbitmq.banking.core.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    @Value("${queue.debit-transaction}")
    private String debitTransactionQueue;

    @Value("${queue.transfer-transaction}")
    private String transferTransactionQueue;

    @Value("${exchange.banking}")
    private String bankingExchange;

    @Bean(name = "debitTransactionQueue")
    public Queue debitTransactionQueue() {
        return new Queue(debitTransactionQueue);
    }

    @Bean(name = "transferTransactionQueue")
    public Queue transferTransactionQueue() {
        return new Queue(transferTransactionQueue);
    }

    @Bean
    public Exchange exchange() {
        return new DirectExchange(bankingExchange);
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean(name = "debitQueueBinding")
    public Binding debitQueueBinding() {
        return new Binding(debitTransactionQueue,
                Binding.DestinationType.QUEUE,
                bankingExchange,
                debitTransactionQueue,
                null);
    }

    @Bean(name = "transferQueueBinding")
    public Binding transferQueueBinding() {
        return new Binding(transferTransactionQueue,
                Binding.DestinationType.QUEUE,
                bankingExchange,
                transferTransactionQueue,
                null);
    }
}
