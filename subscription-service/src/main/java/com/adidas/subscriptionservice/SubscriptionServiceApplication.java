package com.adidas.subscriptionservice;

import com.adidas.subscriptionservice.messaging.CustomProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableBinding(CustomProcessor.class)
@EnableSwagger2
public class SubscriptionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubscriptionServiceApplication.class, args);
	}

	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/api/v1/**"))
				.apis(RequestHandlerSelectors.basePackage("com.adidas.subscriptionservice.controller"))
				.build()
				.apiInfo(this.apiDetails());
	}

	private ApiInfo apiDetails() {
		return new ApiInfo(
				"Subscription API",
				"API for Subscription service",
				"1.0",
				"",
				new springfox.documentation.service.Contact("Adidas Technologies", "https://kayodeoke.com", "test@gmail.com"),
				"API License",
				"https://kayodeoke.com",
				Collections.emptyList()
		);
	}
}
