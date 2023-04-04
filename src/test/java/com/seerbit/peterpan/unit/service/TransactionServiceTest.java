package com.seerbit.peterpan.unit.service;

import com.seerbit.peterpan.AbstractTest;
import com.seerbit.peterpan.dto.TransactionDto;
import com.seerbit.peterpan.dto.TransactionResponse;
import com.seerbit.peterpan.service.TransactionServiceImpl;
import com.seerbit.peterpan.utils.TestModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
    @BeforeEach
    void setUp() {
        this.transactionService = new TransactionServiceImpl();
    }
    @AfterEach
    void tearDown() {
        this.transactionService.getAllTransaction().clear();
    }
    @Test
    void shouldIncrementListSize_onPostTransaction() {
        populateListAndAssert();
    }
    @Test
    void shouldReturnData_onGetStatistics() throws InterruptedException {
        populateListAndAssert();

        // Wait for 7 seconds, this will make some transactions in the requestList stale
        TimeUnit.SECONDS.sleep(7);

        TransactionResponse response = transactionService.getResponse();
        assertEquals(4, response.getCount());//Only 4 requests would remain
        assertEquals(new BigDecimal("20.50"), response.getMin(), "Minimum will be 20.50");
        assertEquals(new BigDecimal("50.50"), response.getMax(), "Maximum will be 50.50");
        assertEquals(new BigDecimal("142.00"), response.getSum(), "Sum will be 142");
        assertEquals(new BigDecimal("142.00").divide(BigDecimal.valueOf(4)), response.getAvg(), "Sum will be 142");
    }
    @Test
    void shouldDeleteAllData_OnAction() {
        populateListAndAssert();
        this.transactionService.deleteAllTransaction();
        assertEquals(0, transactionService.getAllTransaction().size());//No request will remain
    }
    void populateListAndAssert(){
        //Add to the List Once
        TransactionDto tranRequest = TestModel.createTransaction("30.50", LocalDateTime.now().minusSeconds(27));
        transactionService.createTransaction(tranRequest);
        assertEquals(1, transactionService.getAllTransaction().size(),
                "Size of the Transaction List Should be 1");
        //Add again
        tranRequest = TestModel.createTransaction("50.50", LocalDateTime.now().minusSeconds(15));
        transactionService.createTransaction(tranRequest);
        assertEquals(2, transactionService.getAllTransaction().size(),
                "Size of the Transaction List Should increment by 1");

        //Add again
        tranRequest = TestModel.createTransaction("20.50", LocalDateTime.now().minusSeconds(25));
        transactionService.createTransaction(tranRequest);
        assertEquals(3, transactionService.getAllTransaction().size(),
                "Size of the Transaction List Should increment by 1");

        //Add again
        tranRequest = TestModel.createTransaction("50.50", LocalDateTime.now().minusSeconds(25));
        transactionService.createTransaction(tranRequest);
        assertEquals(4, transactionService.getAllTransaction().size(),
                "Size of the Transaction List Should increment by 1");

        //Add again
        tranRequest = TestModel.createTransaction("20.50", LocalDateTime.now().minusSeconds(5));
        transactionService.createTransaction(tranRequest);
        assertEquals(5, transactionService.getAllTransaction().size(),
                "Size of the Transaction List Should increment by 1");

        //Add again
        tranRequest = TestModel.createTransaction("50.50", LocalDateTime.now().minusSeconds(9));
        transactionService.createTransaction(tranRequest);
        assertEquals(6, transactionService.getAllTransaction().size(),
                "Size of the Transaction List Should increment by 1");

        //Add again
        tranRequest = TestModel.createTransaction("20.50", LocalDateTime.now().minusSeconds(10));
        transactionService.createTransaction(tranRequest);
        assertEquals(7, transactionService.getAllTransaction().size(),
                "Size of the Transaction List Should increment by 1");
    }
}
