package com.deo.microservices.currencyexchangeservice.service;

import com.deo.microservices.currencyexchangeservice.model.CurrencyExchange;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CurrencyExchangeService {

   List<CurrencyExchange> findAll();

   CurrencyExchange findByFromAndTo(String from, String to);
}
