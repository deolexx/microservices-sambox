package com.deo.microservices.smsservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class SmsController {

    @GetMapping("/sms")
    public Mono<String> send() {
        return Mono.just("Hello from sms");
    }

}
