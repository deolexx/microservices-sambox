package com.deo.microservices.smsservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class DelayedCall implements Runnable {

    private final TwilioService service;
    private final String URI;

    @Override
    public void run() {
        log.error("=========BEFORE CALL=======: " + URI);
        // TODO: 02.04.22 uncomment to make actual calls
//            service.call(URI, provider.getPhonenumber());
        log.error("========AFTER CALL=======");

    }
}
