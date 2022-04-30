package com.springboot.my.bank.controllers;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.my.bank.models.BankAccount;
import com.springboot.my.bank.models.Customer;
import com.springboot.my.bank.repository.BankAccountRepository;
import com.springboot.my.bank.repository.CustomerRepository;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private BankAccountRepository bankAccountRepository;

	@GetMapping("/{customerId}")
	public String getCustomerName(@PathVariable Integer customerId) {
		try {
			Customer c = customerRepository.getCustomerById(customerId);
			return c.getName();
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}

	@GetMapping("/{customerId}/accountDetails")
	public BankAccount getAccountDetails(@PathVariable Integer customerId) {
		return bankAccountRepository.getAccountDetailsById(customerId);
	}

	@GetMapping("/{customerId}/showBalance")
	public Double showBalance(@PathVariable Integer customerId) {
		BankAccount b = bankAccountRepository.getAccountDetailsById(customerId);
		return b.returnAccBalance();
	}

}
