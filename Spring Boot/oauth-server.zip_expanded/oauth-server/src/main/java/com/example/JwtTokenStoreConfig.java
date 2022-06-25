package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class JwtTokenStoreConfig {

	@Value("${signature.key}") // get the key from the property file
	private String jwtSigningKey;
	
	//we need to instances 1. Converter to convert the access token to jwt
	// 2. TokenStore that stores the token while converting
	// creating the JwtConverter
	@Bean
	public JwtAccessTokenConverter converter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey(jwtSigningKey);
		return converter;
	}
	// creating the token store that holds the token while converting
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(converter());
	}
}
