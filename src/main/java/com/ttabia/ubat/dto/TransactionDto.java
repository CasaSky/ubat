package com.ttabia.ubat.dto;

import org.web3j.protocol.core.methods.response.Transaction;

import java.math.BigInteger;

public class TransactionDto {

    public final BigInteger value;
    public final String from;
    public final String to;
    public final BigInteger block;
    public final BigInteger gasPrice;
    public final BigInteger gas;

    public TransactionDto(Transaction transaction) {

        this.value = transaction.getValue();
        this.from = transaction.getFrom();
        this.to = transaction.getTo();
        this.block = transaction.getBlockNumber();
        this.gasPrice = transaction.getGasPrice();
        this.gas = transaction.getGas();
    }
}
