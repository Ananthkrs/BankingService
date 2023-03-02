package com.example.Bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Bank.entity.BankAccount;


public interface BankAccountRepository extends CrudRepository<BankAccount,Long>{
	

	
	//public int getAccountBalance(String accountNumber);
	


}
