package com.samhcoco.projects.spring.rabbitmq.banking.core.service;

import com.samhcoco.projects.spring.rabbitmq.banking.core.model.Account;
import com.samhcoco.projects.spring.rabbitmq.banking.core.model.TransferTransaction;

import java.util.Map;

public interface TransferService {

    /**
     * Transfers the given {@link TransferTransaction} from a sender {@link Account}
     * to a target transfer {@link Account}.
     * @param transaction {@link TransferTransaction}.
     * @return {@link TransferTransaction}.
     */
    TransferTransaction transfer(TransferTransaction transaction);

    /**
     * Validates the given transfer {@link TransferTransaction} to
     * verify if a transfer operation is possible.
     * @param transaction {@link TransferTransaction}.
     * @return Failures reason, or empty if validation passed.
     */
    Map<String, String> validate(TransferTransaction transaction);

}
