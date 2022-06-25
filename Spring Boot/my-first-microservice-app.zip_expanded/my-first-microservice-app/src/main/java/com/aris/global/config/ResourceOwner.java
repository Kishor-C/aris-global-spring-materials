package com.aris.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
public class ResourceOwner extends ResourceServerConfigurerAdapter {

	// we need to mention the access rules in the configure method
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/account/**").hasAnyRole("GUEST", "ADMIN")
		.antMatchers(HttpMethod.POST, "/account/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE, "/account/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.PUT, "/account/**").hasAnyRole("GUEST", "ADMIN")
		.anyRequest().authenticated();
	}
}
