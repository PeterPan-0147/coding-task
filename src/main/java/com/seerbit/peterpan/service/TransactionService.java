package com.seerbit.peterpan.service;

import com.seerbit.peterpan.dto.TransactionDto;
import com.seerbit.peterpan.dto.TransactionResponse;
import com.seerbit.peterpan.model.Transaction;

import java.util.Collection;

public interface TransactionService {
    Long createTransaction(TransactionDto transaction);
    void deleteAllTransaction();
    Collection<Transaction> getAllTransaction();
    TransactionResponse getResponse();
}
