package com.acme.demo_integration_test.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "TRANSACTION_LOG")
@Setter @Getter
public class TransactionLog {

    public enum TxType {
        DEBIT, CREDIT
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime creationTime;

    private LocalDateTime txTime;

    private String description;


    private BigDecimal txValue;

    private TxType type;
}
