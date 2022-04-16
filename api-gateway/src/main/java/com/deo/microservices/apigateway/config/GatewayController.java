package com.deo.microservices.apigateway.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
public class GatewayController {

    @GetMapping("/hello")
    Mono<String> getHello() {
        return Mono.just("Hello there");
    }

    @GetMapping("/hi")
    String getHi() {
        return "Hi";
    }

    @GetMapping("/redirect")
    public Mono<Void> indexController(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.PERMANENT_REDIRECT);
        response.getHeaders().setLocation(URI.create("/hello"));
        return response.setComplete();
    }

}
