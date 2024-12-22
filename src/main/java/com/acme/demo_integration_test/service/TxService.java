package com.acme.demo_integration_test.service;

import com.acme.demo_integration_test.dto.NewTxLog;
import com.acme.demo_integration_test.dto.TxLog;
import com.acme.demo_integration_test.mapper.TxMapper;
import com.acme.demo_integration_test.persistence.repository.TxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TxService {
    private final TxRepository repo;
    private final TxMapper mapper;

    public Long addTx(NewTxLog txLog) {
        var entity = mapper.toEntity(txLog);
        return repo.saveAndFlush(entity).getId();
    }

    public List<TxLog> getLogs(LocalDate from ,LocalDate to) {
        return repo.getTxBetween(from.atStartOfDay(), to.atTime(23,59, 59))
                .stream().map(mapper::toDto)
                .toList();
    }
}
