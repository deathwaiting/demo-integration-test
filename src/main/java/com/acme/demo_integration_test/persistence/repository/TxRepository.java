package com.acme.demo_integration_test.persistence.repository;

import com.acme.demo_integration_test.dto.TxLog;
import com.acme.demo_integration_test.persistence.entities.TransactionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TxRepository extends JpaRepository<TransactionLog,Long> {
    @Query("""
            SELECT tx from TransactionLog tx
            WHERE tx.txTime between :from and :to
            ORDER BY tx.txTime DESC
            """)
    List<TransactionLog> getTxBetween(@Param("from") LocalDateTime from, @Param("to")LocalDateTime to);
}
