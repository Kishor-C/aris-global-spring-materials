package com.aris.global.model;

import java.util.Arrays;
import java.util.List;

public class AccountFaker {

	// it has fake account data
	private List<Account> accountsDB = Arrays.asList(
				new Account(1234,10000),
				new Account(2345, 25000),
				new Account(3456, 5000)
			);
	// it returns the account based on the account number
	public Account getAccount(int accountNumber) {
		return accountsDB
				.stream().filter(item -> item.getAccountNumber() == accountNumber)
				.findFirst().get();
	}
	
}
