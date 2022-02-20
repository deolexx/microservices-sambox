package com.deo.microservices.conversionmultipleservice.service;

import com.deo.microservices.conversionmultipleservice.model.Currency;
import com.deo.microservices.conversionmultipleservice.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Currency findBy(String id) {
        return currencyRepository.findById(id).get();
    }

}

