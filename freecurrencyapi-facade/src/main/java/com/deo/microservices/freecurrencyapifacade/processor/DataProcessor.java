package com.deo.microservices.freecurrencyapifacade.processor;

import com.deo.microservices.freecurrencyapifacade.converter.DataConverter;
import com.deo.microservices.freecurrencyapifacade.service.CurrencyValuesLoader;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log4j2
public class DataProcessor {

    private final DataConverter dataConverter;
    private final CurrencyValuesLoader valuesLoader;

    public String process() {
        String baseRates = valuesLoader.getBaseRates();
        String convert = dataConverter.convert(baseRates);
        log.warn(convert, DataProcessor.class);
        return convert;

    }

}
