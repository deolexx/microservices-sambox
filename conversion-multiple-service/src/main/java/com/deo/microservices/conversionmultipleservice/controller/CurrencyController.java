package com.deo.microservices.conversionmultipleservice.controller;

import com.deo.microservices.conversionmultipleservice.model.Currency;
import com.deo.microservices.conversionmultipleservice.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    @PostMapping("/currency")
    public ResponseEntity<Void> createCurrency(@RequestBody Currency currency) {
        currencyService.save(currency);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/currency/{id}")
    public ResponseEntity<Currency> findById(@PathVariable String id) {

        return new ResponseEntity<>(currencyService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/currency")
    public ResponseEntity<List<Currency>> findAll() {
        return new ResponseEntity<>(currencyService.findAll(), HttpStatus.OK);
    }

}
