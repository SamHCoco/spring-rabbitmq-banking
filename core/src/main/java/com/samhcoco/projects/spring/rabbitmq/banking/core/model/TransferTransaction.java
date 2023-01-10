package com.samhcoco.projects.spring.rabbitmq.banking.core.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@Entity
@Table(name = "transfer_transaction")
public class TransferTransaction extends Transaction {

    @Column(name = "receiver_account_id")
    private int receiverAccountId;
}
