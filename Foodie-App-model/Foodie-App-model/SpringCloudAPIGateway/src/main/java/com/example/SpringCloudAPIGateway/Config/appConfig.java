package com.example.SpringCloudAPIGateway.Config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class appConfig {
    @Bean
    public RouteLocator setupRoutes(RouteLocatorBuilder builder)
    {
        return builder.routes()
                .route(predicateSpec -> predicateSpec
                        .path("/api/v1/**")
                        .uri("lb://UserAuthentication"))

                .route(predicateSpec -> predicateSpec
                        .path("/api/v2/**")
                        .uri("lb://user-service"))
                .build();
    }
}
