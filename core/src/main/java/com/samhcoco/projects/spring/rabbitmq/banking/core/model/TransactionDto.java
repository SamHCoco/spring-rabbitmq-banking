package com.samhcoco.projects.spring.rabbitmq.banking.core.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TransactionDto {
    private BigDecimal amount;
    private int accountId;
    private int receivingAccountId;
    private short currencyId;
    private Date created;
    private Date modified;
}
