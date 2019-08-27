package com.ttabia.ubat.service;

public enum CryptoCurrency {

    BTC("Bitcoin", "BTC", "Ƀ", "1"),
    ETH("Ethereum", "ETH", "Ξ", "2");

    private String protocol;
    private String name;
    private String symbol;
    private String id;

    CryptoCurrency(String protocol, String name, String symbol, String id) {
        this.protocol = protocol;
        this.name = name;
        this.symbol = symbol;
        this.id = id;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getId() {
        return id;
    }
}
