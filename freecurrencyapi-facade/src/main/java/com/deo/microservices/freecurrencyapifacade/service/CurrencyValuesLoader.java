package com.deo.microservices.freecurrencyapifacade.service;

public interface CurrencyValuesLoader {

    String getBaseRates();

    String getSpecificRates(String code);

}
