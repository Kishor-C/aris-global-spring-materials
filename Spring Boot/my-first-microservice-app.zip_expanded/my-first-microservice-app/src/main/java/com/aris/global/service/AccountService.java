package com.aris.global.service;

import org.springframework.stereotype.Service;

import com.aris.global.model.Account;
import com.aris.global.model.AccountFaker;

// we need to create interface & implement 
@Service
public class AccountService {

	public String test() {
		return "Hello Account Service";
	}
	
	public Account getAccountByNumber(int number) {
		return new AccountFaker().getAccount(number);
	}
}
