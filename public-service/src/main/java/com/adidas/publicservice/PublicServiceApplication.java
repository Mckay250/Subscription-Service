package com.adidas.publicservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PublicServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PublicServiceApplication.class, args);
	}

	@Bean
	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {

		return builder.routes()
				.route("subscription-service", r -> r.path("/api/v1/subscriptions/*", "/swagger-ui.html")
						.uri("http://subscription-service:8091"))
				.build();
	}

}
