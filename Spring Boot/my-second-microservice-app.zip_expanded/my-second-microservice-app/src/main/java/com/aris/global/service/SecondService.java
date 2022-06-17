package com.aris.global.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SecondService {

	@Autowired
	private RestTemplate template;
	
	/*
	 * Here RestTemplate can distribute requests to all the instances
	 * in Round Robin fashion.
	 */
	public String invokeRemoteService() {
		String url = "http://FIRST-APP/account";
		String result = template.getForObject(url, String.class);
		return result;
	}
	
}
