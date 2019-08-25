package com.ttabia.ubat.controller;

import com.ttabia.ubat.service.UbatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UbatController {

    private static final String ADDRESS = "0xbbe79f7d50e4ff6e8ca61082a8740c86e873b3b8";

    @Autowired
    private UbatService ubatService;

    @GetMapping("/info")
    public ResponseEntity<String> info() throws Exception {

        //TODO json response
        String response = "Client version: " +  ubatService.getClientVersion() + "\n"
                + "Address: " + ADDRESS + "\n"
                + "Block number: " + ubatService.getBlockNumber() + "\n"
                + "Balance: " + ubatService.getBalance(ADDRESS) + " Ξ" + "\n";

        return ResponseEntity.ok(response);
    }
}
