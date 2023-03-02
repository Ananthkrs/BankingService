package com.example.Bank.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Bank.entity.BankTransaction;

@Repository
public interface BankTransactionRepository extends JpaRepository<BankTransaction,Long> {
	
	@Query("SELECT bt FROM BankTransaction bt WHERE bt.account.accountNumber = :accountNumber order by transactionDate desc LIMIT 20")
	
	public  List<BankTransaction> getBankTransactions(@Param("accountNumber")long accountNumber);

}
