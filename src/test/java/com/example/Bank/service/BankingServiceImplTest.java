package com.example.Bank.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.Bank.dao.BankAccountRepository;
import com.example.Bank.dao.BankTransactionRepository;
import com.example.Bank.entity.BankAccount;
import com.example.Bank.entity.BankTransaction;
import com.example.Bank.model.UserTransaction;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class BankingServiceImplTest {

	@Mock
	BankAccountRepository bankAccountRepository;

	@Mock
	BankTransactionRepository bankTransactionRepository;

	@Autowired
	@InjectMocks
	BankingServiceImpl bankingServiceImpl;

	@Test
	public void testCreditInterest() {
		BankAccount bankAccount = new BankAccount();
		bankAccount.setAccountNumber(2000);
		bankAccount.setCustomerName("Ramesh");
		bankAccount.setAvailableBalance(8000);
		bankAccount.setCustomerEmail("ramesh@gmail.com");
		Date date = Date.valueOf(LocalDate.now());
		bankAccount.setAccountCreationDate(date);
		Mockito.when(bankAccountRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(bankAccount));
		int creditInterest = bankingServiceImpl.creditInterest(1000);
		Assertions.assertEquals(creditInterest, 26);

	}

	@Test
	public void testDebitInterest() {
		BankAccount bankAccount = new BankAccount();
		bankAccount.setAccountNumber(2000);
		bankAccount.setCustomerName("Ramesh");
		bankAccount.setAvailableBalance(-8000);
		bankAccount.setCustomerEmail("ramesh@gmail.com");
		Date date = Date.valueOf(LocalDate.now());
		bankAccount.setAccountCreationDate(date);
		Mockito.when(bankAccountRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(bankAccount));
		int debitInterest = bankingServiceImpl.debitInterest(1000);
		Assertions.assertEquals(debitInterest, 13);

	}

	@Test
	public void testWithdrawMoney() {
	
		BankAccount bankAccount = new BankAccount();
		bankAccount.setAccountNumber(2000);
		bankAccount.setCustomerName("Ramesh");
		bankAccount.setAvailableBalance(5000);
		bankAccount.setCustomerEmail("ramesh@gmail.com");
		Date date = Date.valueOf(LocalDate.now());
		bankAccount.setAccountCreationDate(date);
		Mockito.when(bankAccountRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(bankAccount));
		int availableBalance = bankingServiceImpl.withdrawMoney(2000, 1000);
		Assertions.assertEquals(availableBalance, 3000);

	}

	@Test

	public void testDepositMoney() {
		BankAccount bankAccount = new BankAccount();
		bankAccount.setAccountNumber(2000);
		bankAccount.setCustomerName("Ramesh");
		bankAccount.setAvailableBalance(5000);
		bankAccount.setCustomerEmail("ramesh@gmail.com");
		Date date = Date.valueOf(LocalDate.now());
		bankAccount.setAccountCreationDate(date);
		Mockito.when(bankAccountRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(bankAccount));
		int availableBalance = bankingServiceImpl.depositMoney(2000, 1000);
		Assertions.assertEquals(availableBalance, 7000);

	}

	@Test

	public void testGetCurrentBalance() {
		BankAccount bankAccount = new BankAccount();
		bankAccount.setAccountNumber(2000);
		bankAccount.setCustomerName("Ramesh");
		bankAccount.setAvailableBalance(5000);
		bankAccount.setCustomerEmail("ramesh@gmail.com");
		Date date = Date.valueOf(LocalDate.now());
		bankAccount.setAccountCreationDate(date);
		Mockito.when(bankAccountRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(bankAccount));
		int currentBalance = bankingServiceImpl.getCurrentBalance(2000);
		Assertions.assertEquals(currentBalance, 5000);

	}

	@Test

	public void testGetTransactionsList() {

		BankAccount bankAccount = new BankAccount();
		bankAccount.setAccountNumber(2000);
		bankAccount.setCustomerName("Ramesh");
		bankAccount.setAvailableBalance(5000);
		bankAccount.setCustomerEmail("ramesh@gmail.com");
		Date date = Date.valueOf(LocalDate.now());
		bankAccount.setAccountCreationDate(date);
		BankTransaction bankTransaction = new BankTransaction();
		bankTransaction.setAccount(bankAccount);
		bankTransaction.setTransactionAmount(1000);
		Date transactionDate = Date.valueOf(LocalDate.now());
		bankTransaction.setTransactionDate(transactionDate);
		bankTransaction.setTransactionType("CREDIT");
		bankTransaction.setCurrentBalance(3000);
		bankTransaction.setDebitBalance(0);
		List<BankTransaction> transactionList = new ArrayList<>();
		transactionList.add(bankTransaction);
		Mockito.when(bankAccountRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(bankAccount));
		Mockito.when(bankTransactionRepository.getBankTransactions(Mockito.anyLong())).thenReturn(transactionList);
		List<UserTransaction> userTransactionList = bankingServiceImpl.getBankTransactions(2000);
		Assertions.assertEquals(userTransactionList.size(), 1);

	}

}
