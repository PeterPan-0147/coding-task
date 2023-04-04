package com.seerbit.peterpan.unit.service;

import com.seerbit.peterpan.AbstractTest;
import com.seerbit.peterpan.dto.TransactionDto;
import com.seerbit.peterpan.dto.TransactionResponse;
import com.seerbit.peterpan.service.TransactionServiceImpl;
import com.seerbit.peterpan.utils.TestModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest extends AbstractTest {
    @InjectMocks
    TransactionServiceImpl transactionService;
    @Test
    void shouldIncrementListSize_onPostTransaction() {
        populateListAndAssert();
    }
    @Test
    void shouldReturnData_onGetStatistics() throws InterruptedException {
        populateListAndAssert();

        TimeUnit.SECONDS.sleep(7);

        TransactionResponse response = transactionService.getResponse();
        assertEquals(3, response.getCount());//Only 4 requests would remain
        assertEquals(new BigDecimal("20.50"), response.getMin(), "Minimum will be 20.50");
        assertEquals(new BigDecimal("50.50"), response.getMax(), "Maximum will be 50.50");
        assertEquals(new BigDecimal("121.50"), response.getSum(), "Sum will be 142");
        assertEquals(new BigDecimal("121.50").divide(BigDecimal.valueOf(3)), response.getAvg(), "Sum will be 121");
    }
    @Test
    void shouldDeleteAllData_OnAction() {
        populateListAndAssert();
        this.transactionService.deleteAllTransaction();
        assertEquals(0, transactionService.getAllTransaction().size());
    }
    void populateListAndAssert() {
        double[] amounts = {30.50, 50.50, 20.50, 50.50, 20.50, 50.50};
        int[] seconds = {27, 15, 25, 25, 5, 9};

        for (int i = 0; i < amounts.length; i++) {
            TransactionDto tranRequest = TestModel.createTransaction(
                    String.valueOf(amounts[i]), LocalDateTime.now().minusSeconds(seconds[i]));
            transactionService.createTransaction(tranRequest);
            assertEquals(i + 1, transactionService.getAllTransaction().size(),
                    "Size of the Transaction List Should increment by 1");
        }
    }
}
