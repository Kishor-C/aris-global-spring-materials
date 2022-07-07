package com.aris.global.util;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.aris.global.model.Account;

@FeignClient("http://zuul-api/first-app")
public interface AccountClient {

	// this below method calls remote service i.e., http://FIRST-APP/account/1234
	@GetMapping("/account/{accountNumber}")
	public Account spendingAmount(@PathVariable("accountNumber") int accountNumber);
}
