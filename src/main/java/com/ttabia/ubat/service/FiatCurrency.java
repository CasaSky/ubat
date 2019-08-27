package com.ttabia.ubat.service;

import java.util.Arrays;
import java.util.List;

public enum FiatCurrency {

    EUR("Euro", "EUR", "€", "10"),
    USD("US-Dollar", "USD", "$", "11");

    private String name;
    private String code;
    private String symbol;
    private String id;

    FiatCurrency(String name, String code, String symbol, String id) {
        this.name = name;
        this.code = code;
        this.symbol = symbol;
        this.id = id;
    }

    public static List<FiatCurrency> currencies() {

        return Arrays.asList(values());
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getId() {
        return id;
    }
}
