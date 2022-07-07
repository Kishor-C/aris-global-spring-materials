package com.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
public class ResourceOwner extends ResourceServerConfigurerAdapter {

	// this method will have the access rule
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET).hasAnyRole("GUEST", "ADMIN")
		.antMatchers(HttpMethod.POST).hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE).hasRole("ADMIN")
		.antMatchers(HttpMethod.PUT).hasAnyRole("GUEST", "ADMIN")
		.anyRequest().authenticated();	
	}
}
