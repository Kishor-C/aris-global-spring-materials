package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class UserOAuth2Config extends WebSecurityConfigurerAdapter{

	// We need to define user & their roles
	// we can create AuthenticationManager & UserDetailsServiceBean object that is used for generating the token
	@Override
	// called to register the user details & roles
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("alex").password("{noop}alex123").roles("ADMIN")
		.and()
		.withUser("bruce").password("{noop}bruce123").roles("GUEST");
	}

	@Override // AuthenticationManager handles authentication in spring security
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}

	@Override // UserDetailsServiceBean is used to hold user information
	@Bean
	public UserDetailsService userDetailsServiceBean() throws Exception {
		// TODO Auto-generated method stub
		return super.userDetailsServiceBean();
	}	
}
