package com.ttabia.ubat.dto;

import java.math.BigDecimal;

public class Conversion {

    private String currency;
    private String symbol;
    private BigDecimal price;

    public Conversion(String currency, String symbol, BigDecimal price) {
        this.currency = currency;
        this.symbol = symbol;
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public String getSymbol() {
        return symbol;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
