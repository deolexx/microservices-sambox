package com.deo.microservices.freecurrencyapifacade.service;

public interface CurrencyValuesLoader {

    void getBaseRates();

    void getSpecificRates(String code);

}
