package com.samhcoco.projects.spring.rabbitmq.banking.core.model;

import com.samhcoco.projects.spring.rabbitmq.banking.core.Auditable;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
@ToString(callSuper = true)
public class Transaction extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "account_id")
    private int accountId;

    @Column(name = "currency_id")
    private short currencyId;
}
