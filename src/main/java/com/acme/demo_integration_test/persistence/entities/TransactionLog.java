package com.acme.demo_integration_test.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;
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
    @Column(name = "CREATION_TIME")
    private LocalDateTime creationTime;

    @Column(name = "TX_TIME")
    private LocalDateTime txTime;

    private String description;

    @Column(name = "TX_VALUE")
    private BigDecimal txValue;

    @Enumerated(STRING)
    private TxType type;
}
