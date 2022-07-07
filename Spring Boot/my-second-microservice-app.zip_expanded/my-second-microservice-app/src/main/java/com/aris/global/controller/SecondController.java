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

import com.aris.global.cfg.Config;
import com.aris.global.model.Wallet;
import com.aris.global.service.SecondService;

@RestController
@RequestMapping("/wallet")
public class SecondController {

	// import from org.slf4j.Logger
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SecondService service;
	
	@Autowired
	private Config cfg;
	
	@GetMapping
	public String testingExternalProperties() {
		return cfg.getPropertyMessage();
	}
	
	@GetMapping("/{accountNumber}")
	public ResponseEntity<Object> fetchWalletDetails(@PathVariable("accountNumber") int accountNumber) {
		LOG.info("*********fetchWalletDetails of Second Microservice************");
		Wallet wallet = service.walletDetails(accountNumber);
		return ResponseEntity.status(HttpStatus.OK).body(wallet);
	}
}
