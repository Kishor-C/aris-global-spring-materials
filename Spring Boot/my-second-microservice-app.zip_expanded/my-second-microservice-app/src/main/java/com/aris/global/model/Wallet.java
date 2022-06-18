package com.aris.global.model;

public class Wallet {
	private String name;
	private Account account;
	private double walletBalance = 1500;
	private double totalAmount;
	
	public Wallet(String name, Account account, double totalAmount) {
		super();
		this.name = name;
		this.account = account;
		this.totalAmount = totalAmount;
	}
	
	public Wallet(String name, Account account) {
		super();
		this.name = name;
		this.account = account;
	}

	public Wallet() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public double getWalletBalance() {
		return walletBalance;
	}
	public void setWalletBalance(double walletBalance) {
		this.walletBalance = walletBalance;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
}
