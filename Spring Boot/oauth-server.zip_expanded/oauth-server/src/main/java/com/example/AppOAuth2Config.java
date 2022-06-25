package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
public class AppOAuth2Config extends AuthorizationServerConfigurerAdapter {
	/*
	 * This takes care of registering the application and also it uses an AuthenticationManager
	 * to authenticate the user & the application
	 */
	// this is used by spring security to handle the authentication
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtAccessTokenConverter tokenConverter; // token converter
	@Autowired
	private TokenStore tokenStore; // token store
	// this object holds user information
	@Autowired
	private UserDetailsService userDetailsService;
	// the below methods are automatically called to generate token & perform authentication
	// this must register the client application, we can give some credentials in hard-coded
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
		.withClient("my-client")
		.secret("{noop}secretpassword")
		.authorizedGrantTypes("password")
		.scopes("web", "mobile");
		// all these informations the client sends behind the scene in the request header
	}
	// this method generates the token based on authentication manager, userdetails
	// and also if you want to use JWT
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
		.tokenStore(tokenStore)
		.accessTokenConverter(tokenConverter)
		.authenticationManager(authenticationManager)
		.userDetailsService(userDetailsService);
	}
	
	
	
}
