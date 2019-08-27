package com.ttabia.ubat.dto;

import java.math.BigDecimal;
import java.util.List;

public class ConversionResponse {

    private String currency;
    private BigDecimal amount;
    private List<Conversion> conversions;

    public ConversionResponse(String currency, BigDecimal amount, List<Conversion> conversions) {
        this.currency = currency;
        this.amount = amount;
        this.conversions = conversions;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public List<Conversion> getConversions() {
        return conversions;
    }
}
