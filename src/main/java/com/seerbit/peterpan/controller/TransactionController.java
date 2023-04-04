package com.seerbit.peterpan.controller;

import com.seerbit.peterpan.apiresponse.ApiDataResponse;
import com.seerbit.peterpan.dto.IdResponseDto;
import com.seerbit.peterpan.dto.TransactionDto;
import com.seerbit.peterpan.dto.TransactionResponse;
import com.seerbit.peterpan.exception.UnprocessableEntityException;
import com.seerbit.peterpan.service.TransactionService;
import com.seerbit.peterpan.service.TransactionServiceImpl;
import com.seerbit.peterpan.utils.ApiResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController implements ITransactionController {
    private final TransactionService transactionService;

    @Override
    @PostMapping
    public ResponseEntity<ApiDataResponse<IdResponseDto>> createTransaction(TransactionDto dto) {
        LocalDateTime timestamp = dto.getTimeStamp();

        if(TransactionServiceImpl.isFuture(timestamp)){
            throw new UnprocessableEntityException("Fields are not parsable or the transaction date is in the future");
        }
        if (!isValidAmount(dto.getAmount())){
            throw new UnprocessableEntityException("Input a value greater than 0.00");
        }
        if (isTimestampOlderThan30Seconds(timestamp)) {
            // handle error case for old timestamp
            return ApiResponseUtils.response(HttpStatus.NO_CONTENT,"Transaction is older than 30 seconds!!!");
        } else {
            // process transaction

            Long id = transactionService.createTransaction(dto);
            return ApiResponseUtils.response(HttpStatus.CREATED, new IdResponseDto(id), "Resource created successfully");
        }
    }
    public static boolean isValidAmount(BigDecimal amount) {
        // Check if the value is greater than zero and has no more than 4 decimal places
        return amount != null &&
                amount.compareTo(BigDecimal.ZERO) > 0 &&
                amount.scale() <= 4;
    }
    @Override
    @DeleteMapping
    public ResponseEntity<ApiDataResponse<IdResponseDto>> deleteTransactions() {
        transactionService.deleteAllTransaction();
        return ApiResponseUtils.response(HttpStatus.NO_CONTENT, "Resource deleted successfully");
    }
    @Override
    @GetMapping
    public ResponseEntity<TransactionResponse> getTransactionResponse() {
        TransactionResponse response = transactionService.getResponse();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    public static boolean isTimestampOlderThan30Seconds(LocalDateTime timestamp) {
        return timestamp.isBefore(LocalDateTime.now().minusSeconds(30));
    }
}
