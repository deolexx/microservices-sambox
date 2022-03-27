package com.deo.microservices.pdfexportservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class PdfController {

    @GetMapping("/pdf")
    private Mono<String> create() {
        return Mono.just("Hello from pdf");
    }
}
