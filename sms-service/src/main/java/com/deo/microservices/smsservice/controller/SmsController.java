package com.deo.microservices.smsservice.controller;

import com.deo.microservices.smsservice.service.SpringScheduler;
import com.deo.microservices.smsservice.service.TwilioService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class SmsController {

    private final TwilioService twilioService;
    private final SpringScheduler scheduler;

    @PostMapping("/sms")
    public Mono<String> send(@Validated @RequestParam String text, @Validated @RequestParam String number) {
        twilioService.sendSms(text, number);
        return Mono.just("queued");
    }

    @PostMapping("/call")
    public Mono<String> call(@Validated @RequestParam String number) {
        twilioService.twimlCall(number);
        return Mono.just("queued");
    }


    @PostMapping("/callremote")
    public Mono<String> callremote(@Validated @RequestParam String number) {
        twilioService.prerecordedCall(number);
        return Mono.just("queued");
    }
}
