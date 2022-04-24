package com.deo.microservices.securityservice.controller;

import com.deo.microservices.securityservice.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MethodRoleSecurityController {

    private final Service service;

    @GetMapping("/user")
    public String userOnly() {
        return service.onlyUser();
    }

    @GetMapping("/admin")
    public String adminOnly() {
        return service.onlyAdmin();
    }

    @GetMapping("/both")
    public String userAndAdmin() {
        return service.userAndAdmin();
    }

    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is public";
    }

    @GetMapping("/private")
    public String privateEndpoint() {
        return "This is private";
    }
}
