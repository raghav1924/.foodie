package com.project.GatewayApp.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Bean
    public RouteLocator getroute(RouteLocatorBuilder builder)
    {
        return builder.routes()
                .route(p->p
                        .path("/userAuth/**")
                        .uri("http://localhost:9999/*"))
                .route(p->p
                        .path("/sellerAuth/**")
                        .uri("http://localhost:8888/*"))
                .route(p->p
                        .path("/sellerService/**")
                        .uri("http://localhost:888/*"))
                .route(p->p
                        .path("/restaurantService/**")
                        .uri("http://localhost:888/*"))
                .route(p->p
                        .path("/userService/**")
                        .uri("http://localhost:999/*"))
                .route(p->p
                        .path("/orderService/**")
                        .uri("http://localhost:999/*"))
                .build();
    }
}
