package com.seerbit.peterpan.utils;

import com.seerbit.peterpan.dto.TransactionDto;
import com.seerbit.peterpan.dto.TransactionResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public final class TestModel {
    public TestModel() {
    }
    public static TransactionResponse createResponse(){
        return TransactionResponse.builder()
                .max(new BigDecimal("10.50"))
                .min(new BigDecimal("2.33"))
                .avg(new BigDecimal("9.00"))
                .sum(new BigDecimal("27.00"))
                .count(3L).build();
    }
    public static TransactionDto createTransaction(String amount, LocalDateTime dateTime){
        return TransactionDto.builder()
                .timeStamp(dateTime)
                .amount(new BigDecimal(amount))
                .build();
    }
}
