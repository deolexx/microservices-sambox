package com.deo.microservices.freecurrencyapifacade.converter;

import com.deo.microservices.freecurrencyapifacade.mappper.CurrencyDataMapper;
import com.deo.microservices.freecurrencyapifacade.model.CurrencyData;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log4j2
public class DataConverter {
    @Autowired
    private CurrencyDataMapper mapper;

    public String convert(String data) {
        CurrencyData currencyData = mapper.fromString(data);
        log.warn(currencyData.getQuery());
        return currencyData.getData();

    }

}
