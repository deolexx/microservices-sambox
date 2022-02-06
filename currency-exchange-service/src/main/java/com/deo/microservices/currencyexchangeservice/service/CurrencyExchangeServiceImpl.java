package com.deo.microservices.currencyexchangeservice.service;

import com.deo.microservices.currencyexchangeservice.model.CurrencyExchange;
import com.deo.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {

    private final CurrencyExchangeRepository currencyExchangeRepository;

    @Override
    public List<CurrencyExchange> findAll() {
        return currencyExchangeRepository.findAll();
    }

    @Override
    public CurrencyExchange findByFromAndTo(String from, String to) {
        return currencyExchangeRepository.findByFromAndTo(from,to);
    }
}
