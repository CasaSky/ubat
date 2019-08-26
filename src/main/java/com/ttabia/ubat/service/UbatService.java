package com.ttabia.ubat.service;

import com.ttabia.ubat.client.Web3jClient;
import com.ttabia.ubat.dto.InfoDto;
import com.ttabia.ubat.dto.TransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.core.methods.response.Transaction;

@Service
public class UbatService {

    @Autowired
    private Web3jClient web3jClient;

    private static final double WEI_TO_ETH = 1e18;

    public InfoDto getInfo(String address) throws Exception {

        return new InfoDto(getClientVersion(), address, getBlockNumber(), getBalance(address) + " Ξ");
    }

    public String getClientVersion() throws Exception {

        return web3jClient.getClientVersion();
    }

    public String getBalance(String address) throws Exception {

        return String.valueOf(web3jClient.getBalance(address).doubleValue() / WEI_TO_ETH);
    }

    public String getBlockNumber() throws Exception {

        return String.valueOf(web3jClient.getBlockNumber());
    }

    public TransactionDto getTransactionDetail(String transactionHash) throws Exception {

        Transaction transaction = web3jClient.getTransactionDetail(transactionHash).orElseThrow(TransactionHashNotFoundException::new);
        return new TransactionDto(transaction);
    }
}
