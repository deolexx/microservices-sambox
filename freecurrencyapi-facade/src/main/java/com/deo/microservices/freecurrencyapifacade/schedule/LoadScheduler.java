package com.deo.microservices.freecurrencyapifacade.schedule;

import com.deo.microservices.freecurrencyapifacade.processor.DataProcessor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class LoadScheduler {
    @Autowired
    DataProcessor dataProcessor;

//    @Scheduled(cron = "* */1 * * * *")
    public String cronUAH() {
        String rates = dataProcessor.process();
        log.warn(rates, LoadScheduler.class);
        return rates;

    }

}
