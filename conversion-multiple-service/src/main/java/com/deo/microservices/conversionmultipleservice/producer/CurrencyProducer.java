package com.deo.microservices.conversionmultipleservice.producer;

import com.deo.microservices.conversionmultipleservice.model.Currency;
import com.deo.microservices.conversionmultipleservice.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Log4j2
public class CurrencyProducer {

    @Value(value = "${kafka.topic.name}")
    private String topicName;
    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private KafkaTemplate<String, List<Currency>> kafkaTemplate;

    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
    public void sendMessage() {
        List<Currency> currencies = currencyService.findAll();
        ListenableFuture<SendResult<String, List<Currency>>> future =
                kafkaTemplate.send(topicName, currencies);

        future.addCallback(new ListenableFutureCallback<SendResult<String, List<Currency>>>() {

            @Override
            public void onSuccess(SendResult<String, List<Currency>> result) {
                System.out.println("Sent message=[" + currencies.toString() +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=["
                        + currencies.toString() + "] due to : " + ex.getMessage());
            }
        });
    }

}
