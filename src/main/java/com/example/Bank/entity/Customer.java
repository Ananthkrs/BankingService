package com.example.Bank.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long customerId;
	private String customerfirstName;
	private String customerLastName;
	private String customerEmail;
	private String customerAddress;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="accountNumber")
	private BankAccount account;
	

}
