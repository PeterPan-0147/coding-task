package com.seerbit.peterpan.controller;

import com.seerbit.peterpan.apiresponse.ApiDataResponse;
import com.seerbit.peterpan.dto.IdResponseDto;
import com.seerbit.peterpan.dto.TransactionDto;
import com.seerbit.peterpan.dto.TransactionResponse;
import io.swagger.annotations.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Api(tags = { "Transaction Controller"})
public interface ITransactionController {
        @ApiResponses(value = {
                @ApiResponse(code = 201, message = "Resource created successfully"),
                @ApiResponse(code = 204, message = "Transaction is older than 30 seconds!!!"),
                @ApiResponse(code = 400, message = "JSON is invalid!!!"),
                @ApiResponse(code = 422, message = "Fields are not parsable or the transaction date is in the future")})
        ResponseEntity<ApiDataResponse<IdResponseDto>> createTransaction(
                @Valid @RequestBody TransactionDto dto);
        @ApiResponses(value = {
                @ApiResponse(code = 204, message = "Resources deleted successfully")})
        ResponseEntity<ApiDataResponse<IdResponseDto>> deleteTransactions();

        @ApiResponses(value = {
                @ApiResponse(code = 200, message = "Resource retrieved successfully")})
        ResponseEntity<TransactionResponse> getTransactionResponse();





}
