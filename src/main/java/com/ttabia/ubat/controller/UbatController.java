package com.ttabia.ubat.controller;

import com.ttabia.ubat.dto.InfoDto;
import com.ttabia.ubat.dto.TransactionDto;
import com.ttabia.ubat.service.UbatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UbatController {

    private static final String ADDRESS = "0xbbe79f7d50e4ff6e8ca61082a8740c86e873b3b8";
    private static final String TRANSACTION_HASH = "0xd014161ce38549f79ceee8cfbcede920726fe8b50ea748b72a1cff65330158c4";

    @Autowired
    private UbatService ubatService;

    @GetMapping("/info/ethereum")
    public ResponseEntity<InfoDto> info() throws Exception {

        return ResponseEntity.ok(ubatService.getInfo(ADDRESS));
    }

    @GetMapping("/transactionDetail/ethereum")
    public ResponseEntity<TransactionDto> transactionDetail() throws Exception {

        return ResponseEntity.ok(ubatService.getTransactionDetail(TRANSACTION_HASH));
    }
}
