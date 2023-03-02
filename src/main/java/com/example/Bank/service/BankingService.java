package com.example.Bank.service;

import java.util.List;

import com.example.Bank.model.*;

import org.springframework.stereotype.Service;

@Service
public interface BankingService {
	
	
	
	
	public int withdrawMoney(int amount,long accountNumber);
	
	public int depositMoney(int amount,long accountNumber);
	
	public List<UserTransaction> getBankTransactions(long accountNumber);
	
	public int creditInterest(long accountNumber);
	
	public int debitInterest(long accountNumber);
	
	public int getCurrentBalance(long accountNumber);
	
	
	

}
