package com.acme.demo_integration_test.dto;

import com.acme.demo_integration_test.persistence.entities.TransactionLog;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TxLog {
    private Long id;
    private String description;
    private LocalDateTime creationTime;
    private LocalDateTime txTime;
    private BigDecimal txValue;
    private TransactionLog.TxType type;
}
