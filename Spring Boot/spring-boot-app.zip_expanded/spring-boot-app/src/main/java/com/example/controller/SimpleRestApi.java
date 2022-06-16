package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.beans.User;

@RestController
@RequestMapping("simple")
public class SimpleRestApi {

	// path to access this service http://ip:port-no/simple/welcome
	@GetMapping(path = "/welcome")
	public String greet() {
		return "Hello Rest API";
	}
	
	// this is to return complex object i.e., User
	@GetMapping(path = "/user")
	public User fetch() {
		User user = new User();
		user.setUserId(1234);
		user.setName("Raj");
		return user;
	}
}
