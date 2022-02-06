package com.deo.mictoservices.currencyconversionservice.controller;

import com.deo.mictoservices.currencyconversionservice.model.CurrencyConversion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity) {

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, uriVariables);
        CurrencyConversion body = responseEntity.getBody();
        return CurrencyConversion.builder()
                .id(body.getId())
                .from(from)
                .to(to)
                .conversionMultiple(body.getConversionMultiple())
                .environment(body.getEnvironment())
                .totalCalculatedAmount(body.getConversionMultiple().multiply(quantity))
                .build();
    }

}
