package com.acme.demo_integration_test.controller;

import com.acme.demo_integration_test.dto.NewTxLog;
import com.acme.demo_integration_test.dto.TxLog;
import com.acme.demo_integration_test.service.TxService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@RestController
@RequiredArgsConstructor
@RequestMapping("tx")
public class TxController {

    private final TxService txService;

    @PostMapping
    public Long addTx(@Validated @RequestBody NewTxLog txLog) {
        return this.txService.addTx(txLog);
    }

    @GetMapping
    public List<TxLog> getTx(
            @RequestParam(value = "from", required = false) LocalDate from,
            @RequestParam(value = "to", required = false) LocalDate to) {
        var dateFrom = ofNullable(from).orElse(LocalDate.of(1900, 1, 1));
        var dateTo = ofNullable(to).orElse(LocalDate.of(3900, 1, 1));
        return this.txService.getLogs(dateFrom, dateTo);
    }
}
