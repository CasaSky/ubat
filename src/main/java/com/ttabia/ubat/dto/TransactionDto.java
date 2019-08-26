package com.ttabia.ubat.dto;

import org.web3j.protocol.core.methods.response.Transaction;

import java.math.BigInteger;

public class TransactionDto {

    private BigInteger value;
    private String from;
    private String to;
    private BigInteger block;
    private BigInteger gasPrice;
    private BigInteger gas;

    public TransactionDto(Transaction transaction) {

        this.value = transaction.getValue();
        this.from = transaction.getFrom();
        this.to = transaction.getTo();
        this.block = transaction.getBlockNumber();
        this.gasPrice = transaction.getGasPrice();
        this.gas = transaction.getGas();
    }

    public BigInteger getValue() {
        return value;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public BigInteger getBlock() {
        return block;
    }

    public BigInteger getGasPrice() {
        return gasPrice;
    }

    public BigInteger getGas() {
        return gas;
    }
}
