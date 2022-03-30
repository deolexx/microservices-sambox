package com.deo.microservices.smsservice.controller;

import com.deo.microservices.smsservice.model.MessageInput;
import com.deo.microservices.smsservice.service.TwilioSimpleSmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class SmsController {

    private final TwilioSimpleSmsService smsService;

    @PostMapping("/sms")
    public Mono<String> send(@Validated @RequestBody MessageInput input) {

        return Mono.just(smsService.send(input));
    }

    @PostMapping("/call")
    public Mono<String> call(@RequestParam("name") String name) {
        return Mono.just(smsService.call(name));
    }
}
