package com.samhcoco.projects.spring.rabbitmq.banking.account.model;

import com.samhcoco.projects.spring.rabbitmq.banking.core.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "account")
public class Account extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "user_id")
    private int userId;
}
