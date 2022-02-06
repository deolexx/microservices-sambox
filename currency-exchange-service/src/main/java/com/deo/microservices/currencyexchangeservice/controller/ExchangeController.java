package com.deo.microservices.currencyexchangeservice.controller;

import com.deo.microservices.currencyexchangeservice.model.CurrencyExchange;
import com.deo.microservices.currencyexchangeservice.service.CurrencyExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExchangeController {
    @Autowired
    private Environment environment;

    private final CurrencyExchangeService currencyExchangeService;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        CurrencyExchange currencyExchange = currencyExchangeService.findByFromAndTo(from, to);
        currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
        return currencyExchange;
    }
}
