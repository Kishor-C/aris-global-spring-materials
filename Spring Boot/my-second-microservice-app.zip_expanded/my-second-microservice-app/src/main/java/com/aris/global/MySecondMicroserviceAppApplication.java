package com.aris.global;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class MySecondMicroserviceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySecondMicroserviceAppApplication.class, args);
	}

	// creating RestTemplate with Ribbon aware Load Balancer
	// we need to also register the RestTemplate in the spring context
	
	@LoadBalanced // now rest template can use logical name
	@Bean // registers the RestTemplate in the spring container
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
