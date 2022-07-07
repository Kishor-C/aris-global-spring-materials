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

// registers the application credentials configuration
// use 2 beans AuthenticationManager & UserDetailsService to authenticate
@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authMgr;	
	@Autowired
	private UserDetailsService userDetails;
	@Autowired
	private TokenStore tokenStore;
	@Autowired
	private JwtAccessTokenConverter tokenConverter;
	
	// method that configures and registers application credentials
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
		.withClient("my-app")
		.secret("{noop}secretpassword")
		.authorizedGrantTypes("password")
		.scopes("web", "mobile"); // all these properties the client application must send for authentication
	}
	// method that stores the user tokens and also can enhance user tokens like JWT
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
		.tokenStore(tokenStore)
		.accessTokenConverter(tokenConverter)
		.authenticationManager(authMgr)
		.userDetailsService(userDetails);
	}
}
