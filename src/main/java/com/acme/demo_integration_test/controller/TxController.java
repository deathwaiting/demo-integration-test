package com.acme.demo_integration_test.controller;

import com.acme.demo_integration_test.dto.NewTxLog;
import com.acme.demo_integration_test.service.TxService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("tx")
public class TxController {

    private final TxService txService;

    @PostMapping
    public Long addTx(@RequestBody NewTxLog txLog) {
        return this.txService.addTx(txLog);
    }
}
