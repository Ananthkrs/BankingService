package com.example.Bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.Bank.entity.*;
import com.example.Bank.model.UserTransaction;

import org.springframework.web.bind.annotation.*;

import com.example.Bank.service.BankingService;

@RestController
@RequestMapping("api/bank")

public class BankingServiceController {

	@Autowired
	BankingService bankingService;

	/**
	 * This api method is used to perform withdrawal transaction request based on
	 * withdrawal amount and account number
	 * @param accountNumber
	 * @return balance:available balance after the withdrawal transaction
	 */
	@RequestMapping("/withdrawMoney")
	public int withdrawRequest(@RequestParam int amount, @RequestParam long accountNumber) {
		int balance = bankingService.withdrawMoney(amount, accountNumber);
		return balance;
	}

	/**
	 * This api method is used to perform deposit transaction request based on
	 * withdrawal amount
	 * @param amount
	 * @param accountNumber
	 * @return balance:available balance after the deposit transaction
	 */
	@RequestMapping("/depositMoney")
	public int depositRequest(@RequestParam int amount, @RequestParam long accountNumber) {
		int balance = bankingService.depositMoney(amount, accountNumber);
		return balance;
	}

	/**
	 * This api method is used to get the current balance for the specific account
	 * number
	 * @param accountNumber
	 * @return balance:latest available balance for the specific account number
	 */
	@GetMapping("/getBalance")
	public int getCurrentBalance(@RequestParam long accountNumber) {
		int balance = bankingService.getCurrentBalance(accountNumber);
		return balance;
	}

	/**
	 * This api method is used to calculate the credit interest for the specific
	 * account number based on outstanding balance
	 * 
	 * @param accountNumber
	 * @return creditInterestAmount:credited interest amount for the specific bank
	 *         account
	 */

	@RequestMapping("/creditInterest")
	public int calculateCreditInterest(long accountNumber) {
		int creditInterestAmount = bankingService.creditInterest(accountNumber);
		return creditInterestAmount;

	}

	/**
	 * This api method is used to calculate the debit interest for the specific
	 * account number in case of overdraft or negative balance
	 * @param accountNumber
	 * @return debitInterestAmount:debited interest amount for the specific bank
	 *         account
	 */

	@RequestMapping("/debitInterest")
	public int calculateDebitInterest(long accountNumber) {
		int debitInterestAmount = bankingService.debitInterest(accountNumber);
		return debitInterestAmount;
	}

	/**
	 * This api method is used to fetch last 20 transaction for the specific bank
	 * account
	 * @param accountNumber
	 * @return list
	 */
	@RequestMapping("/getTransactions")
	public List<UserTransaction> getUserTransactionList(long accountNumber) {
		List<UserTransaction> transactionList = bankingService.getBankTransactions(accountNumber);
		return transactionList;
	}
}
