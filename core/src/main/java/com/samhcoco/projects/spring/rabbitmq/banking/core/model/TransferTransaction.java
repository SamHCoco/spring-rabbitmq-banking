package com.samhcoco.projects.spring.rabbitmq.banking.core.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TransferTransaction extends Transaction {
    private int id;
    private int receivingAccountId;
}
