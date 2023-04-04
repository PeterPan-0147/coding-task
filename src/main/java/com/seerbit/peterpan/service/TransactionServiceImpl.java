package com.seerbit.peterpan.service;

import com.seerbit.peterpan.dto.TransactionDto;
import com.seerbit.peterpan.dto.TransactionResponse;
import com.seerbit.peterpan.exception.UnprocessableEntityException;
import com.seerbit.peterpan.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService{
    private final List<Transaction> transactions = Collections.synchronizedList( new ArrayList<>());
    @Override
    public Long createTransaction(TransactionDto dto) {
        if(isFuture(dto.getTimeStamp())){
            throw new UnprocessableEntityException("Fields are not parsable or the transaction date is in the future");
        }
        try {
            if (dto.getTimeStamp().isAfter(LocalDateTime.now())) {
                System.out.println("Debug");
                throw new UnprocessableEntityException("Fields are not parsable or the transaction date is in the future");
            }
            Transaction transaction = this.mapToTransaction(dto);
            this.transactions.add(transaction);
            int index = this.transactions.indexOf(transaction);
            return Long.valueOf(index);
        } catch (DateTimeParseException e) {
            throw new UnprocessableEntityException("Invalid timestamp format");
        }
    }
    private Transaction mapToTransaction(TransactionDto dto) {
        return Transaction.builder()
                .amount(dto.getAmount())
                .timeStamp(dto.getTimeStamp())
                .build();
    }
    public static boolean isFuture(LocalDateTime dateTime) {
        return dateTime.isAfter(LocalDateTime.now());
    }
    @Override
    public void deleteAllTransaction() {
        this.transactions.clear();
    }
    @Override
    public Collection<Transaction> getAllTransaction() {
        return this.transactions;
    }
    @Override
    public TransactionResponse getResponse() {
        if (this.transactions.isEmpty()){
            return new TransactionResponse();
        }
        LocalDateTime now = LocalDateTime.now();

        BigDecimal maximum = new BigDecimal("0.00");
        BigDecimal minimum = null;
        BigDecimal sum = new BigDecimal("0.00");
        BigDecimal average = new BigDecimal("0.00");
        Long count = 0L;
        synchronized (this.getAllTransaction()) {
            for (Transaction transaction : this.getAllTransaction()) {
                if (transaction.getTimeStamp().isBefore(now.minusSeconds(30))) {
                    continue;
                }
                BigDecimal amount = transaction.getAmount();
                if (amount.compareTo(maximum) > 0) {
                    maximum = amount;
                }
                if (minimum == null || amount.compareTo(minimum) < 0) {
                    minimum = amount;
                }
                sum = sum.add(amount);
                count++;
            }
        }
        maximum = maximum.setScale(2, RoundingMode.HALF_UP);
        minimum = minimum.setScale(2, RoundingMode.HALF_UP);
        sum = sum.setScale(2, RoundingMode.HALF_UP);

        BigDecimal avg = sum.divide(BigDecimal.valueOf(count), 2, RoundingMode.HALF_UP);

        return TransactionResponse.builder()
                .max(maximum)
                .min(minimum)
                .sum(sum)
                .avg(avg)
                .count(count)
                .build();
    }
}
