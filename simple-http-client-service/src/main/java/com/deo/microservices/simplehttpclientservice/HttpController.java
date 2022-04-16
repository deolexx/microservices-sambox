package com.deo.microservices.simplehttpclientservice;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HttpController {

    private final HttpService service;

    @GetMapping("/get")
    public void get() {
        service.sendReceive();
    }

}
