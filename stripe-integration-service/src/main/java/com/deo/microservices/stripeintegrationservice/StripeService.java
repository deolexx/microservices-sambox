package com.deo.microservices.stripeintegrationservice;

import com.stripe.Stripe;
import com.stripe.model.*;
import com.stripe.net.RequestOptions;
import com.stripe.param.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
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

}
