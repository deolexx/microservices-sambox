package com.deo.microservices.conversionmultipleservice.converter;

import com.deo.microservices.conversionmultipleservice.model.Currency;
import com.google.gson.Gson;

public class CurrencyConverter {
    private final Gson gson = new Gson();

    public String toJson(Currency currency) {
        return gson.toJson(currency);
    }

    public Currency fromJson(String message) {
        return gson.fromJson(message, Currency.class);
    }

}
