package com.aris.global.controller;

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

	@Autowired
	private AccountService accountService;
	
	@GetMapping
	public ResponseEntity<Object> greet() {
		System.out.println("****** First Microservice is called ********");
		String result = accountService.test();
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	@GetMapping("/{accountNumber}")
	public ResponseEntity<Object> getAccount(@PathVariable("accountNumber") int accountNumber) {
		System.out.println("*********** fetching: '"+accountNumber+"' ***********");
		Account account = accountService.getAccountByNumber(accountNumber);
		return ResponseEntity.status(HttpStatus.OK).body(account);
	}
}
