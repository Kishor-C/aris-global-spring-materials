package com.aris.global.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aris.global.model.Account;
import com.aris.global.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {

	// import from org.slf4j
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping
	public ResponseEntity<Object> greet() {
		
		String result = accountService.test();
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	@GetMapping("/{accountNumber}")
	public ResponseEntity<Object> getAccount(@PathVariable("accountNumber") int accountNumber) {
		LOG.info("*************getAccount of First Microservice*****************");
		Account account = accountService.getAccountByNumber(accountNumber);
		return ResponseEntity.status(HttpStatus.OK).body(account);
	}
}
