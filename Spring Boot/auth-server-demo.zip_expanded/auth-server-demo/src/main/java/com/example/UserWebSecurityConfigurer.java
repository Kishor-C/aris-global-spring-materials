package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

// register as a bean in the container through @Configuration as it has user details hard code
@Configuration
public class UserWebSecurityConfigurer extends WebSecurityConfigurerAdapter {
	
	// register the UserDetailsService in the spring which stores the user information
	@Override
	@Bean
	public UserDetailsService userDetailsServiceBean() throws Exception {
		// TODO Auto-generated method stub
		return super.userDetailsServiceBean();
	}
	// register the AuthenticationManagerBean to handle user & client authentication
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}
	// configure the user credentials to store in UserDetailsService that should be used by authentication manager
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// through auth the user credentials can be registered in UserDetailsService 
		auth.inMemoryAuthentication()
		.withUser("alex").password("{noop}alex123").roles("GUEST", "ADMIN")
		.and()
		.withUser("bruce").password("{noop}bruce123").roles("GUEST");
	}
}
