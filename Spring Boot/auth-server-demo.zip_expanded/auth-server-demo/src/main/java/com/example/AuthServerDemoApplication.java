package com.example;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableAuthorizationServer // this is a token end point so that you can use /oauth/token to generate the token
@EnableResourceServer // it can intercept OAuth2 token, it is a protected resource
@RestController // to create an end point to handle read the token and return the response
public class AuthServerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServerDemoApplication.class, args);
	}
	
	/*
	 * This method accepts the token and returns the response with token details if its valid else returns unauthorized
	 * This end point is accessed by any microservice using any HTTP methods 
	 * The argument takes the token which will have the details and converts to a OAuth2Authentication 
	 * that will have client & user details
	 */
	@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> handleToken(OAuth2Authentication userAndClient) {
		Map<String, Object> user = new HashMap<String, Object>();
		//userAndClient of OAuth2Authentication has some functions to specify users & their roles 
		//we can those user & roles in the map
		user.put("user", userAndClient.getUserAuthentication().getPrincipal()); // authenticated user informations
		user.put("authorities", userAndClient.getUserAuthentication().getAuthorities());
		return user;
	}
}
