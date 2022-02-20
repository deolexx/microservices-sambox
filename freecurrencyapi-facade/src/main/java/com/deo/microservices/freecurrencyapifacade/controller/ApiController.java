package com.deo.microservices.freecurrencyapifacade.controller;

import com.deo.microservices.freecurrencyapifacade.service.CurrencyValuesLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiController {

    @Autowired
    CurrencyValuesLoader valuesLoader;

    @GetMapping("/latest")
    public void getBaseRates() {
        valuesLoader.getBaseRates();
    }

    @GetMapping("/latest/{code}")
    public void getSpecificRates(@PathVariable String code) {
        valuesLoader.getSpecificRates(code);
    }
}
