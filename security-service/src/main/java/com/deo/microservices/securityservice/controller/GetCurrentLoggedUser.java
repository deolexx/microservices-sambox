package com.deo.microservices.securityservice.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetCurrentLoggedUser {

    //All three methods do the same
    @GetMapping("/1")
    public String simpleGet() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "HELLO THERE " + authentication.getName();
    }
    //Now we use autowiring
    @GetMapping("/2")
    public String methodArgumentGet(Authentication authentication) {
        return "HELLO THERE " + authentication.getName();
    }
    //Using annotation we could extract needed properties
    @GetMapping("/3")
    public String annotationGet(@CurrentSecurityContext(expression = "authentication.name") String name) {
        return "HELLO THERE " + name;
    }

}
