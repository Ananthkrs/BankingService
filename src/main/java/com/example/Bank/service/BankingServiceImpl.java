package com.example.Bank.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.Bank.dao.BankAccountRepository;
import com.example.Bank.dao.BankTransactionRepository;
import com.example.Bank.entity.*;
import com.example.Bank.model.*;

@Service
public class BankingServiceImpl implements BankingService {

	@Autowired
	private BankAccountRepository bankAccountRepository;

	@Autowired
	private BankTransactionRepository bankTransactionRepository;

	@Override
	/**
	 * This method is used to record withdrawal transaction for specific account
	 * number
	 * 
	 * @param amount
	 * @param accountNumber
	 * @return currentBalance:available account after the transaction
	 */
	public int withdrawMoney(int amount, long accountNumber) {

		int currentBalance = 0;
		Optional<BankAccount> account = bankAccountRepository.findById(accountNumber);
		if (account.isPresent()) {

			BankAccount bankAccount = account.get();
			int balance = bankAccount.getAvailableBalance();
			currentBalance = balance - amount;

			BankTransaction bankTransaction = new BankTransaction();
			bankTransaction.setAccount(bankAccount);
			bankTransaction.setTransactionAmount(amount);
			bankTransaction.setTransactionType("DEBIT");
			if (currentBalance < 0) {
				bankTransaction.setDebitBalance(currentBalance);
			} else {
				bankTransaction.setCurrentBalance(currentBalance);
			}
			Date date = Date.valueOf(LocalDate.now());
			bankTransaction.setTransactionDate(date);
			bankTransactionRepository.save(bankTransaction);
			bankAccount.setAvailableBalance(currentBalance);
			bankAccountRepository.save(bankAccount);

		}
		return currentBalance;

	}

	@Override
	/**
	 * This method is used to record deposit transaction for specific account number
	 * 
	 * @param amount
	 * @param accountNumber
	 * @return currentBalance:available account after the transaction
	 */
	public int depositMoney(int amount, long accountNumber) {
		// TODO Auto-generated method stub

		int currentBalance = 0;

		Optional<BankAccount> account = bankAccountRepository.findById(accountNumber);
		if (account.isPresent()) {

			BankAccount bankAccount = account.get();
			int balance = bankAccount.getAvailableBalance();
			currentBalance = balance + amount;

			if (currentBalance > 0) {
				BankTransaction bankTransaction = new BankTransaction();
				bankTransaction.setAccount(bankAccount);
				bankTransaction.setTransactionAmount(amount);
				bankTransaction.setTransactionType("CREDIT");
				bankTransaction.setCurrentBalance(currentBalance);
				Date date = Date.valueOf(LocalDate.now());
				bankTransaction.setTransactionDate(date);
				bankTransactionRepository.save(bankTransaction);
				bankAccount.setAvailableBalance(currentBalance);
				bankAccountRepository.save(bankAccount);

			}

		}
		return currentBalance;

	}

	@Override
	/**
	 * This method is used to get the last 20 transaction for the specific account
	 * 
	 * @param accountNumber
	 * @return transactionList:list contains last 20 transactions for the account
	 */
	public List<UserTransaction> getBankTransactions(long accountNumber) {
		// TODO Auto-generated method stub
		List<UserTransaction> transactionList = new ArrayList<>();
		Optional<BankAccount> account = bankAccountRepository.findById(accountNumber);

		if (account.isPresent()) {
			BankAccount bankAccount = account.get();
			List<BankTransaction> transactions = bankTransactionRepository.getBankTransactions(accountNumber);
			transactions.forEach(transaction -> {
				UserTransaction userTransaction = new UserTransaction();
				userTransaction.setAccountNumber(accountNumber);
				userTransaction.setCustomerName(bankAccount.getCustomerName());
				userTransaction.setTransactionAmount(transaction.getTransactionAmount());
				userTransaction.setTransactionDate(transaction.getTransactionDate());
				userTransaction.setTransactionType(transaction.getTransactionType());
				userTransaction.setCurrentBalance(transaction.getCurrentBalance());
				userTransaction.setDebitBalance(transaction.getDebitBalance());
				transactionList.add(userTransaction);

			});

		}

		return transactionList;
	}

	@Override
	/**
	 * This method is used calculate the interest to be credited for the specific
	 * account. This is based 4% monthly interest on outstanding balance
	 * 
	 * @param accountNumber
	 * @return creditInterest:interest credited to the bank account
	 */
	public int creditInterest(long accountNumber) {
		// TODO Auto-generated method stub
		int creditInterest = 0;
		Optional<BankAccount> account = bankAccountRepository.findById(accountNumber);
		if (account.isPresent()) {
			BankAccount bankAccount = account.get();
			int availableBalance = bankAccount.getAvailableBalance();
			double interestAmount = availableBalance * 0.04 * 30 / 365;
			creditInterest = (int) interestAmount;

			bankAccount.setAvailableBalance(availableBalance + creditInterest);
			BankTransaction bankTransaction = new BankTransaction();
			bankTransaction.setAccount(bankAccount);
			bankTransaction.setCurrentBalance(availableBalance + creditInterest);
			bankTransaction.setTransactionAmount(creditInterest);
			bankTransaction.setTransactionType("CREDIT INTEREST");
			Date date = Date.valueOf(LocalDate.now());
			bankTransaction.setTransactionDate(date);

			bankTransactionRepository.save(bankTransaction);
			bankAccountRepository.save(bankAccount);

		}

		return creditInterest;

	}

	@Override
	/**
	 * This method is used calculate the interest to be credited for the specific
	 * account. This is based 2% monthly interest on negative or overdraft balance
	 * 
	 * @param accountNumber
	 * @return debitInterest:interest debited to the bank account
	 */
	public int debitInterest(long accountNumber) {
		// TODO Auto-generated method stub
		int debitInterest = 0;
		Optional<BankAccount> account = bankAccountRepository.findById(accountNumber);
		if (account.isPresent()) {
			BankAccount bankAccount = account.get();
			int currentBalance = bankAccount.getAvailableBalance();
			if (currentBalance < 0) {
				double interestAmount = currentBalance * 0.02 * 30 / 365;
				debitInterest = (int) interestAmount;
				bankAccount.setAvailableBalance(currentBalance + debitInterest);
				BankTransaction bankTransaction = new BankTransaction();
				bankTransaction.setAccount(bankAccount);
				bankTransaction.setTransactionAmount(Math.abs(debitInterest));
				bankTransaction.setDebitBalance(currentBalance + debitInterest);
				bankTransaction.setTransactionType("DEBIT INTEREST");
				Date date = Date.valueOf(LocalDate.now());
				bankTransaction.setTransactionDate(date);
				bankTransactionRepository.save(bankTransaction);
				bankAccountRepository.save(bankAccount);

			}

		}

		return Math.abs(debitInterest);

	}

	@Override
	/**
	 * This method is used to get the current balance of the bank account
	 * 
	 * @param accountNumber
	 * @return currentBalance:current balance of the bank account
	 */
	public int getCurrentBalance(long accountNumber) {
		int currentBalance = 0;
		Optional<BankAccount> account = bankAccountRepository.findById(accountNumber);
		if (account.isPresent()) {
			BankAccount bankAccount = account.get();
			currentBalance = bankAccount.getAvailableBalance();

		}

		return currentBalance;
	}

}
