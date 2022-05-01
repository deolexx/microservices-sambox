package com.deo.microservices.apigateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.net.URI;

@Slf4j
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

    @GetMapping("/success")
    String succes() {
        log.warn("SUCCESS CALLED");
        return "success";
    }

    @GetMapping("/refresh")
    String refresh() {
        log.warn("REFRESH CALLED");
        return "refresh";
    }
}
