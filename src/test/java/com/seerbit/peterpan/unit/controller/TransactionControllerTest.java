package com.seerbit.peterpan.unit.controller;

import com.seerbit.peterpan.AbstractTest;
import com.seerbit.peterpan.controller.TransactionController;
import com.seerbit.peterpan.dto.TransactionDto;
import com.seerbit.peterpan.dto.TransactionResponse;
import com.seerbit.peterpan.model.Transaction;
import com.seerbit.peterpan.service.TransactionService;
import com.seerbit.peterpan.utils.TestModel;
import com.seerbit.peterpan.utils.TestUtils;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
@ActiveProfiles("test")
public class TransactionControllerTest extends AbstractTest {
    @MockBean
    TransactionService transactionService;
    private final String path = "/api/v1/transactions";

    @BeforeEach
    void setUp() {
        when(transactionService.getResponse()).thenReturn(TestModel.createResponse());
    }
    @Test
    public void shouldDeleteTransactionsSuccessfully() throws Exception {
        doNothing().when(this.transactionService).deleteAllTransaction();
        mockMvc.perform(delete(path))
                .andExpect(status().isNoContent());
    }
    @Test
    void shouldCreateTransactionSuccessfully() throws Exception {
        //input
        TransactionDto dto = TransactionDto.builder()
                .amount(new BigDecimal("10.4567"))
                        .timeStamp(LocalDateTime.now())
                                .build();
        //act
        this.performPostRequest(dto)
                .andExpect(status().isCreated());

        ArgumentCaptor<TransactionDto> dtoArgumentCaptor = ArgumentCaptor.forClass(TransactionDto.class);
        verify(this.transactionService).createTransaction(dtoArgumentCaptor.capture());
        assertTrue(new ReflectionEquals(dto).matches(dtoArgumentCaptor.getValue()));
    }
    @Test
    void ShouldReturnStatusNoContent_whenTimestampIsStaleByAtLeast30Seconds() throws Exception {
        //input
        TransactionDto dto = TransactionDto.builder()
                .amount(new BigDecimal("10.4567"))
                .timeStamp(LocalDateTime.parse("2022-04-04T01:30:51.312Z", ISO_DATE_TIME ))
                .build();
        //act
        this.performPostRequest(dto)
                .andExpect(status().isNoContent());
    }
    @Test
    void ShouldReturnStatusUnprocessable_whenFutureTimeStampIsPassed() throws Exception{
        TransactionDto dto = TransactionDto.builder()
                .amount(new BigDecimal("10.45676"))
                .timeStamp(LocalDateTime.now().plusHours(1))
                .build();
        //act
        this.performPostRequest(dto)
                .andExpect(status().isUnprocessableEntity());
    }
    @Test
    void ShouldReturnStatusUnprocessable_whenInvalidAmountIsPassed() throws Exception{
        TransactionDto dto = TransactionDto.builder()
                .amount(new BigDecimal("10.234555"))
                .timeStamp(LocalDateTime.now())
                .build();
        //act
        this.performPostRequest(dto)
                .andExpect(status().isUnprocessableEntity());
    }
    @Test
    void whenTransactionStatisticsIsFetched_shouldReturnSuccess() throws Exception {
        mockMvc.perform(get(path)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.max", Is.is(10.50)))
                .andExpect(jsonPath("$.min", Is.is(2.33)))
                .andExpect(jsonPath("$.avg", Is.is(9.00)))
                .andExpect(jsonPath("$.sum", Is.is(27.00)))
                .andExpect(jsonPath("$.count", Is.is(3)))
                .andDo(print());
    }
    private ResultActions performPostRequest(TransactionDto dto) throws Exception {
        return this.mockMvc.perform(post(path)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.asJsonString(dto))
        );
    }
}
