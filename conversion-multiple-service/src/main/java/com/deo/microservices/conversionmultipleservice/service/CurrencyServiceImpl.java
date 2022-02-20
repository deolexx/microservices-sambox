package com.deo.microservices.conversionmultipleservice.service;

import com.deo.microservices.conversionmultipleservice.model.Currency;
import com.deo.microservices.conversionmultipleservice.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private final CurrencyRepository currencyRepository;

    @Override
    public void save(Currency currency) {
        currencyRepository.save(currency);
    }

    @Override
    public Currency findById(String id) {
        return currencyRepository.findById(id).get();
    }

    @Override
    public List<Currency> findAll() {
        List<Currency> currencies = new ArrayList<>();
        currencyRepository.findAll().forEach(currencies::add);
        return currencies;
    }

}

