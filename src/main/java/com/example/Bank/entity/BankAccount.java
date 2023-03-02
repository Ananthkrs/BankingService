package com.example.Bank.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
 

@Entity

public class BankAccount {
	
public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public int getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(int availableBalance) {
		this.availableBalance = availableBalance;
	}

	public Date getAccountCreationDate() {
		return accountCreationDate;
	}

	public void setAccountCreationDate(Date accountCreationDate) {
		this.accountCreationDate = accountCreationDate;
	}

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private long accountNumber;
	
private String customerName;

private String customerEmail;

private int availableBalance;

private Date accountCreationDate;

//@OneToMany(mappedBy="account")
//private List<BankTransaction> bankTransactions = new ArrayList<>();
//
//public List<BankTransaction> getBankTransactions() {
//	return bankTransactions;
//}
//
//public void setBankTransactions(List<BankTransaction> bankTransactions) {
//	this.bankTransactions = bankTransactions;
}










