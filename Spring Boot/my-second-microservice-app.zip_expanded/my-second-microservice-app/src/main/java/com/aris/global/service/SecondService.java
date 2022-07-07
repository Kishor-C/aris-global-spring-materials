package com.aris.global.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.aris.global.model.Account;
import com.aris.global.model.Wallet;
import com.aris.global.util.AccountClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class SecondService {

	//@Autowired
	//private RestTemplate template;
	// autowire the feign client
	@Autowired
	private AccountClient accountClient;
	// Autowire OAuth2RestTemplate
	@Autowired
	private OAuth2RestTemplate oauth2Client;
	
	//create a method that returns the wallet by calling first microservice
	@CircuitBreaker(name = "callAccountService", fallbackMethod = "fallback")
	public Wallet walletDetails(int accountNumber) {
		System.out.println("*****Call goes to the remote service*****");
		// calling remote service
		//Account account = accountClient.spendingAmount(accountNumber);
		// calling first/account/{number} via zuul & Oauth2RestTemplate
		String url = "http://zuul-api/first/account/"+accountNumber;
		Account account = oauth2Client.getForObject(url, Account.class);
		// initialize wallet object using the account we get from the first microservice
		Wallet wallet = new Wallet("MyPay", account); // here we have an initial balance of 1500 
		double totalAmount = wallet.getWalletBalance() + account.getBalance();
		wallet.setTotalAmount(totalAmount);
		return wallet;
	}
	public Wallet fallback(int accountNumber, Throwable t) {
		System.out.println("****Call came to fallback*****");
		t.printStackTrace();
		Wallet wallet = new Wallet();
		wallet.setName("MyPay");
		wallet.setAccount(new Account());
		return wallet;
	}
	/*
	 * Here RestTemplate can distribute requests to all the instances
	 * in Round Robin fashion.
	 */
//	public String invokeRemoteService() {
//		String url = "http://FIRST-APP/account";
//		String result = template.getForObject(url, String.class);
//		return result;
//	}
	
}
