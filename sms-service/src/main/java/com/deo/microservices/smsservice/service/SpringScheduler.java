package com.deo.microservices.smsservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Configuration
@RequiredArgsConstructor
public class SpringScheduler {

    private final TwilioService service;
    private final ThreadPoolTaskScheduler scheduler;

    public void delayedCall() {
        scheduler.schedule(new DelayedCall(service, "https://microservices-sandbox-2316.twil.io/m1.mp3"), Instant.now().plus(10, ChronoUnit.SECONDS));
        scheduler.schedule(new DelayedCall(service, "https://microservices-sandbox-2316.twil.io/m2.mp3"), Instant.now().plus(20, ChronoUnit.SECONDS));
        scheduler.schedule(new DelayedCall(service, "https://microservices-sandbox-2316.twil.io/m3.mp3"), Instant.now().plus(30, ChronoUnit.SECONDS));
        scheduler.schedule(new DelayedCall(service, "https://microservices-sandbox-2316.twil.io/m4.mp3"), Instant.now().plus(40, ChronoUnit.SECONDS));
    }
}
