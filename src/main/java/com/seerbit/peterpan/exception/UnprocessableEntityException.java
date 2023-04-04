package com.seerbit.peterpan.exception;

public class UnprocessableEntityException extends RuntimeException{
    private final static String ERROR_CODE = "422";

    public UnprocessableEntityException(String message) {
        super(ERROR_CODE);
    }
}
