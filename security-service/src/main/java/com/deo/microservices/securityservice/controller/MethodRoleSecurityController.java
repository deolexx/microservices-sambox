package com.deo.microservices.securityservice.controller;

import com.deo.microservices.securityservice.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MethodRoleSecurityController {

    private final Service service;

    @GetMapping("/ring")
    public String whoIsTheBearer() {
        return service.isBearer();
    }

    @GetMapping("/hobbit")
    public String isUserAHobbit() {
        return service.isHobbit();
    }

}
