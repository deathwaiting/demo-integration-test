package com.acme.demo_integration_test.mapper;

import com.acme.demo_integration_test.dto.NewTxLog;
import com.acme.demo_integration_test.dto.TxLog;
import com.acme.demo_integration_test.persistence.entities.TransactionLog;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TxMapper {
    TransactionLog toEntity(NewTxLog newLog);
    TxLog toDto(TransactionLog entity);
}
