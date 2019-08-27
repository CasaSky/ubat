package com.ttabia.ubat.controller;

import com.ttabia.ubat.dto.ConversionResponse;
import com.ttabia.ubat.dto.InfoDto;
import com.ttabia.ubat.dto.TransactionDto;
import com.ttabia.ubat.service.UbatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;


@RestController
public class UbatController {

    @Autowired
    private UbatService ubatService;

    @GetMapping("/info/ethereum/{address}")
    public ResponseEntity<InfoDto> info(@PathVariable String address) throws Exception {

        return ResponseEntity.ok(ubatService.getInfo(address));
    }

    @GetMapping("/transactionDetail/ethereum/{transactionHash}")
    public ResponseEntity<TransactionDto> transactionDetail(@PathVariable String transactionHash) throws Exception {

        return ResponseEntity.ok(ubatService.getTransactionDetail(transactionHash));
    }

    @GetMapping("/conversion")
    public ResponseEntity<ConversionResponse> cryptoToFiat(@RequestParam String currency, @RequestParam BigDecimal amount)
            throws Exception {

        return ResponseEntity.ok(ubatService.cryptoToFiat(currency, amount));
    }
}
