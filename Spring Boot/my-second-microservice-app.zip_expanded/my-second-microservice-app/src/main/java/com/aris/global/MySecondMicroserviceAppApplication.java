package com.aris.global;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableResourceServer
@EnableOAuth2Client
public class MySecondMicroserviceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySecondMicroserviceAppApplication.class, args);
	}

	// creating OAuth2ProtectedResource Details for OAuth2RestTemplate
	@Bean
	public OAuth2ProtectedResourceDetails resource() {
		return new AuthorizationCodeResourceDetails();
	}
	//passing the Protected Resource Details to the OAuth2RestTemplate
	@Bean
	@LoadBalanced
	public OAuth2RestTemplate oauth2RestTemplate(
			OAuth2ClientContext context,
			OAuth2ProtectedResourceDetails resourceDetails
			) {
		return new OAuth2RestTemplate(resource(), context);
	}
	// creating RestTemplate with Ribbon aware Load Balancer
	// we need to also register the RestTemplate in the spring context
	
//	@LoadBalanced // now rest template can use logical name
//	@Bean // registers the RestTemplate in the spring container
//	public RestTemplate restTemplate(RestTemplateBuilder builder) {
//		return builder.build();
//	}
	

}
