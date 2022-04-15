package com.deo.microservices.stripeintegrationservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Stripe Service API", version = "1.0",
                description = "process payment service integrated with Stripe"
        )
)
public class StripeIntegrationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StripeIntegrationServiceApplication.class, args);
    }

}
