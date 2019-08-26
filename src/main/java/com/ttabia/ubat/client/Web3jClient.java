package com.ttabia.ubat.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Component
public class Web3jClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(Web3jClient.class);

    private static final String INFURA_MAINNET = "https://mainnet.infura.io/";

    private Web3j web3;

    @Autowired
    private Environment environment;

    public String getClientVersion() throws Exception {

        try {
            Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().send();
            return web3ClientVersion.getWeb3ClientVersion();
        } catch (IOException e) {
            LOGGER.error("Internal error", e);
            throw new Exception(e.getMessage());
        }
    }

    public BigInteger getBalance(String address) throws Exception {

        try {
            return web3.ethGetBalance(address, DefaultBlockParameterName.LATEST).sendAsync().get().getBalance();
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.error("Internal error", e);
            throw new Exception(e.getMessage());
        }
    }

    public BigInteger getBlockNumber() throws Exception {

        try {
            return web3.ethBlockNumber().send().getBlockNumber();
        } catch (IOException e) {
            LOGGER.error("Internal error", e);
            throw new Exception(e.getMessage());
        }
    }

    public Optional<Transaction> getTransactionDetail(String transactionHash) throws Exception {

        return web3.ethGetTransactionByHash(transactionHash).sendAsync().get().getTransaction();
    }


    @PostConstruct
    public void connect() {

        LOGGER.info("Building client with infura main net...");
        String projectId = environment.getProperty("infura-project-id");
        web3 = Web3j.build(new HttpService(INFURA_MAINNET + projectId));  // defaults to http://localhost:8545/
    }

    @PreDestroy
    public void close() {

        web3.shutdown();
        LOGGER.info("terminating web3j client.");
    }
}