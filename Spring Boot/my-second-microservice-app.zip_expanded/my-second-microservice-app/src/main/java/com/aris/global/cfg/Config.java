package com.aris.global.cfg;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
public class Config {
	
	@Value("${message}") // injects value of message
	private String propertyMessage;
	
	public String getPropertyMessage() {
		return propertyMessage;
	}
}
