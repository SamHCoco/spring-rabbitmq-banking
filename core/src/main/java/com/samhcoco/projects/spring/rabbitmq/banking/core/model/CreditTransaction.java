package com.samhcoco.projects.spring.rabbitmq.banking.core.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "credit_transaction")
public class CreditTransaction extends Transaction {

    @Column(name = "exchange_rate")
    private BigDecimal exchangeRate;
}
