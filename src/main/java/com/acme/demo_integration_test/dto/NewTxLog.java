package com.acme.demo_integration_test.dto;

import com.acme.demo_integration_test.persistence.entities.TransactionLog;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class NewTxLog {

    @NotNull
    private TransactionLog.TxType type;

    private LocalDateTime txTime = LocalDateTime.now();

    @Length(min = 3, max = 256)
    private String description;

    @Min(0)
    @NotNull
    private BigDecimal txValue;
}
