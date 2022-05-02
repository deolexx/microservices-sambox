package com.deo.microservices.stripeintegrationservice;

import com.amazonaws.util.IOUtils;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.net.Webhook;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
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

    @GetMapping("/intent-create")
    public ResponseEntity<String> createPayment(@RequestParam Long amount) {
        return ResponseEntity.ok(service.createPaymentIntent(amount));
    }

    @GetMapping("/intent-retrieve")
    public ResponseEntity<String> retrieveIntent(@RequestParam String intentID) {
        return ResponseEntity.ok(service.paymentIntents(intentID));
    }

    @PostMapping("/webhook")
    public void receiveWebhook(HttpServletRequest request, HttpServletResponse response) throws IOException {
        service.handleWebhook(request, response);
    }

    @GetMapping("/secret")
    public String getIntent(@RequestParam Long amount) {
        return service.createIntent(amount);
    }

}
