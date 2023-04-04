package com.seerbit.peterpan.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.seerbit.peterpan.utils.CustomDate;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {
    @NotNull
    @DecimalMin(value = "0.00", inclusive = false, message = "Input a value greater than 0.00")
    @Digits(integer = Integer.MAX_VALUE, message = "Fraction should not be more than 4 decimal place", fraction = 4)
    private BigDecimal amount;
    @NotNull(message = "Date Cannot be null")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @CustomDate
    private LocalDateTime timeStamp;
}
