package com.ttabia.ubat.service;

import com.ttabia.ubat.client.Web3jClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UbatService {

    @Autowired
    private Web3jClient web3jClient;

    public String getClientVersion() throws Exception {

        return web3jClient.getClientVersion();
    }

    public String getBalance(String address) throws Exception {

        return web3jClient.getBalance(address);
    }

    public String getBlockNumber() throws Exception {

        return String.valueOf(web3jClient.getBlockNumber());
    }
}
