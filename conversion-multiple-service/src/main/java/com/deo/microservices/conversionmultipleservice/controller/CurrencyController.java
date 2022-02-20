package com.deo.microservices.conversionmultipleservice.controller;

import com.deo.microservices.conversionmultipleservice.model.Currency;
import com.deo.microservices.conversionmultipleservice.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/currency")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    @PostMapping
    public ResponseEntity<Void> createCurrency(@RequestBody Currency currency) {
        currencyService.save(currency);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Currency> findAll(@RequestParam String id) {

        return new ResponseEntity<>(currencyService.findBy(id), HttpStatus.OK);
    }

}
