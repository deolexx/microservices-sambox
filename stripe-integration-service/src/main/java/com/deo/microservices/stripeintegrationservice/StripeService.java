package com.deo.microservices.stripeintegrationservice;

import com.amazonaws.util.IOUtils;
import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.*;
import com.stripe.model.checkout.Session;
import com.stripe.net.RequestOptions;
import com.stripe.net.Webhook;
import com.stripe.param.*;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class StripeService {

    @Value("${stripe.secret_key}")
    private String secretKey;

    @Value("${stripe.refresh_url}")
    private String refreshUrl;

    @Value("${stripe.return_url}")
    private String returnUrl;

    @Value("${stripe.payment.success}")
    private String successPaymentUrl;

    @Value("${stripe.payment.refresh}")
    private String refreshPaymentUrl;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    @SneakyThrows
    public String createAccount() {
        AccountCreateParams params =
                AccountCreateParams
                        .builder()
                        .setCountry("US")
                        .setType(AccountCreateParams.Type.EXPRESS)
                        .setCapabilities(
                                AccountCreateParams.Capabilities
                                        .builder()
                                        .setCardPayments(
                                                AccountCreateParams.Capabilities.CardPayments
                                                        .builder()
                                                        .setRequested(true)
                                                        .build()
                                        )
                                        .setTransfers(
                                                AccountCreateParams.Capabilities.Transfers
                                                        .builder()
                                                        .setRequested(true)
                                                        .build()
                                        )
                                        .build()
                        )
                        .build();

        Account account = Account.create(params);
        return account.toJson();
    }

    @SneakyThrows
    public String createAccountLink(String accountId) {

        AccountLinkCreateParams params =
                AccountLinkCreateParams
                        .builder()
                        .setAccount(accountId)
                        .setRefreshUrl(refreshUrl)
                        .setReturnUrl(returnUrl)
                        .setType(AccountLinkCreateParams.Type.ACCOUNT_ONBOARDING)
                        .build();

        AccountLink accountLink = AccountLink.create(params);
        return accountLink.toJson();
    }

    @SneakyThrows
    public String createLoginLink(String accountId) {

        LoginLink loginLink = LoginLink.createOnAccount(
                accountId,
                (Map<String, Object>) null,
                null
        );

        return loginLink.toJson();
    }

    @SneakyThrows
    public String retrieveAccount(String accountId) {
        Account account =
                Account.retrieve(accountId);
        return account.toJson();
    }

    @SneakyThrows
    public boolean isAuthorized(String accountId) {
        return Account.retrieve(accountId).getChargesEnabled();
    }

    @SneakyThrows
    public String instantPayout(String accountId, Long amount) {
        PayoutCreateParams params =
                PayoutCreateParams.builder()
                        .setAmount(amount)
                        .setCurrency("usd")
                        .setMethod(PayoutCreateParams.Method.INSTANT)
                        .build();

        RequestOptions requestOptions =
                RequestOptions.builder()
                        .setStripeAccount(accountId)
                        .build();

        Payout payout = Payout.create(params, requestOptions);
        return payout.toJson();
    }

    @SneakyThrows
    public String transferFunds(String accountId, Long amount) {
        TransferCreateParams params =
                TransferCreateParams.builder()
                        .setAmount(amount)
                        .setCurrency("usd")
                        .setDestination(accountId)
                        .build();

        Transfer transfer = Transfer.create(params);
        return transfer.toJson();
    }

    @SneakyThrows
    public String createIntent(Long amount) {
        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount(Math.multiplyExact(100, amount))
                        .setCurrency("USD")
                        .addPaymentMethodType("card")
                        .putMetadata("test metadata", "test metadata value")
                        .build();

        // Create a PaymentIntent with the order amount and currency
        return PaymentIntent.create(params).toJson();
    }

    @SneakyThrows
    public String createPaymentIntent(Long amount) {
        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount(Math.multiplyExact(100, amount))
                        .setCurrency("USD")
                        .addPaymentMethodType("card")
                        .putMetadata("test metadata", "test metadata value")
                        .build();

        // Create a PaymentIntent with the order amount and currency
        PaymentIntent paymentIntent = PaymentIntent.create(params);
        return paymentIntent.getClientSecret() + " : " + paymentIntent.getId();
    }

    @SneakyThrows
    public String paymentIntents(final String paymentIntentId) {
        return PaymentIntent.retrieve(paymentIntentId).toJson();
    }

    @SneakyThrows
    public void handleWebhook(HttpServletRequest request, HttpServletResponse response) {
        String webHookSecret = "whsec_wCwu0hkFUr3q2mhwquh1ZrA7aSL4AJCs";
        String sigHeader = request.getHeader("Stripe-Signature");

        String payload = IOUtils.toString(request.getInputStream());
        if (webHookSecret != null && sigHeader != null) {
            // Only verify the event if you have an endpoint secret defined.
            // Otherwise use the basic event deserialized with GSON.
            try {
                Event event = Webhook.constructEvent(
                        payload, sigHeader, webHookSecret
                );
                log.warn(event.getData().toJson());
                log.error(event.getType());
            } catch (SignatureVerificationException e) {
                // Invalid signature
                System.out.println("⚠️  Webhook error while validating signature.");
                response.setStatus(400);

            }

        }
    }
}
