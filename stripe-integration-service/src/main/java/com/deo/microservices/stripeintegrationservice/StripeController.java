package com.deo.microservices.stripeintegrationservice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StripeController {

    private final StripeService service;

    @GetMapping("/create-login-link")
    public ResponseEntity<String> createLoginLink(@RequestParam String accountId) {
        return ResponseEntity.ok(service.createLoginLink(accountId));
    }

    @GetMapping("/create-account")
    public ResponseEntity<String> createAccount() {

        return ResponseEntity.ok(service.createAccount());
    }

    @PostMapping("/create-account-link")
    public ResponseEntity<String> createAccountLink(@RequestParam String accountId) {
        return ResponseEntity.ok(service.createAccountLink(accountId));
    }

    @GetMapping("/retrieve-account")
    public ResponseEntity<String> retrieveAccount(@RequestParam String accountId) {
        return ResponseEntity.ok(service.retrieveAccount(accountId));
    }

    @GetMapping("/isAuthorized")
    public ResponseEntity<Boolean> isAuthorized(@RequestParam String accountId) {
        return ResponseEntity.ok(service.isAuthorized(accountId));
    }

    @GetMapping("/instantPayout")
    public ResponseEntity<String> instantPayout(@RequestParam String accountId, @RequestParam Long amount) {
        return ResponseEntity.ok(service.instantPayout(accountId, amount));
    }

    @GetMapping("/charge")
    public ResponseEntity<String> charge(@RequestParam String accountId, @RequestParam Long amount) {
        return ResponseEntity.ok(service.transferFunds(accountId, amount));
    }

}
