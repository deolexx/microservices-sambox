package com.deo.microservices.conversionmultipleservice.service;

import com.deo.microservices.conversionmultipleservice.model.Currency;

import java.util.List;

public interface CurrencyService {

    void save(Currency currency);

    Currency findBy(String id);

    List<Currency> findAll();

}
