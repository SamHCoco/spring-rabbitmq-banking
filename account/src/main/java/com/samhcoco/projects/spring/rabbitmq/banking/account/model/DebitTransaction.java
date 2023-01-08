package com.samhcoco.projects.spring.rabbitmq.banking.account.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "debit_transaction")
public class DebitTransaction extends Transaction {

    @Column(name = "exchange_rate")
    private BigDecimal exchangeRate;
}
