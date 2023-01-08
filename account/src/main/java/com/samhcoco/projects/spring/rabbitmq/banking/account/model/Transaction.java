package com.samhcoco.projects.spring.rabbitmq.banking.account.model;

import com.samhcoco.projects.spring.rabbitmq.banking.core.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
public class Transaction extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "account_id")
    private int accountId;

    @Column(name = "currency_id")
    private short currencyId;
}
