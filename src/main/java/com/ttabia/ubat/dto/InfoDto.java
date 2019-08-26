package com.ttabia.ubat.dto;

public class InfoDto {

    private String clientVersion;
    private String address;
    private String blockNumber;
    private String balance;

    public InfoDto(String clientVersion, String address, String blockNumber, String balance) {
        this.clientVersion = clientVersion;
        this.address = address;
        this.blockNumber = blockNumber;
        this.balance = balance;
    }

    public String getClientVersion() {
        return clientVersion;
    }

    public String getAddress() {
        return address;
    }

    public String getBlockNumber() {
        return blockNumber;
    }

    public String getBalance() {
        return balance;
    }
}
