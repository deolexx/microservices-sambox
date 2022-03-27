package com.deo.microservices.apigateway.config;

import org.springdoc.core.SwaggerUiConfigParameters;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    /**
     * Open-Api configuration which allow us to group
     * multiple swagger APIs in gateway-service
     */
    @Bean
    public CommandLineRunner openApiGroups(
            RouteDefinitionLocator locator,
            SwaggerUiConfigParameters swaggerUiParameters) {
        return args -> locator
                .getRouteDefinitions().collectList().block()
                .stream()
                .map(RouteDefinition::getId)
                .filter(id -> id.matches(".*-service"))
                .map(id -> id.replace("-service", ""))
                .forEach(swaggerUiParameters::addGroup);
    }
//    @Bean
//    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route(p -> p.path("/currency-exchange/**")
//                        .uri("lb://currency-exchange")
//                )
//                .route(p -> p.path("/currency-conversion/**")
//                        .uri("lb://currency-conversion")
//                ).route(p -> p.path("/currency-conversion-feign/**")
//                        .uri("lb://currency-conversion")
//                ).route(p -> p.path("/currency-conversion-new/**")
//                        .filters(f -> f.rewritePath("/currency-conversion-new/(?<segment>.*)", "/currency-conversion-feign/${segment}"))
//                        .uri("lb://currency-conversion")
//                )
//                .build();
//    }

}
