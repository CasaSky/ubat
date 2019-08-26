package com.ttabia.ubat.dto;

public class InfoDto {

    public final String clientVersion;
    public final String address;
    public final String blockNumber;
    public final String balance;

    public InfoDto(String clientVersion, String address, String blockNumber, String balance) {
        this.clientVersion = clientVersion;
        this.address = address;
        this.blockNumber = blockNumber;
        this.balance = balance;
    }
}
