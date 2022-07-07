package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class JwtConfig {

	// use the sign.key value
	@Value("${sign.key}")
	private String jwtSignKey;
	
	// register the JwtAccessTokenConverter as a bean which translates OAuth to JWT
	@Bean
	public JwtAccessTokenConverter tokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey(jwtSignKey);
		return converter;
	}
	// register the TokenStore as a bean that stores OAuth token and Converter uses this
	// store for translation
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(tokenConverter());
	}
}
