package com.seerbit.peterpan.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

public class CustomDateValidator implements ConstraintValidator<CustomDate, LocalDateTime> {
    @Override
    public boolean isValid(LocalDateTime localDateTime, ConstraintValidatorContext constraintValidatorContext) {
        if (localDateTime == null){
            return false;
        }
        return !localDateTime.isAfter(LocalDateTime.now());
    }
}
