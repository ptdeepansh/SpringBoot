package com.deepanshu.api_gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
        		.route("redirect_root_to_actuator", r -> r
                        .path("/")
                        .filters(f -> f.redirect(302, "/actuator"))
                        .uri("http://localhost:8084"))  // Dummy URI; required but unused
                    .build();
    }
}