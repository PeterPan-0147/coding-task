package com.seerbit.peterpan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TransactionResponse implements Serializable {
    private Long count = 0L;
    private BigDecimal max = new BigDecimal("0.00");
    private BigDecimal min = new BigDecimal("0.00");
    private BigDecimal sum = new BigDecimal("0.00");
    private BigDecimal avg = new BigDecimal("0.00");
}
