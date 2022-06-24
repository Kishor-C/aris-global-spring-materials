package com.aris.global.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aris.global.model.Account;
import com.aris.global.model.Wallet;
import com.aris.global.util.AccountClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class SecondService {

	@Autowired
	private RestTemplate template;
	
	// autowire the feign client
	@Autowired
	private AccountClient accountClient;
	
	//create a method that returns the wallet by calling first microservice
	@CircuitBreaker(name = "walletInstance", fallbackMethod = "fallback")
	public Wallet walletDetails(int accountNumber) {
		System.out.println("*****Call goes to the remote service*****");
		// calling remote service
		Account account = accountClient.spendingAmount(accountNumber);
		// initialize wallet object using the account we get from the first microservice
		Wallet wallet = new Wallet("MyPay", account); // here we have an initial balance of 1500 
		double totalAmount = wallet.getWalletBalance() + account.getBalance();
		wallet.setTotalAmount(totalAmount);
		return wallet;
	}
	// this is a fallback method which needs to follow same signature with Throwable argument
	public Wallet fallback(int accountNumber, Throwable t) {
		System.out.println("**********Call came to fallback*************");
		System.err.println("Exception is: "+t.getMessage());
		Wallet wallet = new Wallet("DummyWallet", new Account());
		return wallet;
	}
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
