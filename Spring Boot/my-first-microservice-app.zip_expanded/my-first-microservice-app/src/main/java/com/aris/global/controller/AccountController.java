package com.aris.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aris.global.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@GetMapping
	public ResponseEntity<Object> greet() {
		System.out.println("****** First Microservice is called ********");
		String result = accountService.test();
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
}
