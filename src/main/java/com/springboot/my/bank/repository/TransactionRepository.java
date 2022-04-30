/**
 * 
 */
package com.springboot.my.bank.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springboot.my.bank.dao.TransactionDAO;
import com.springboot.my.bank.mappers.TransactionMapper;
import com.springboot.my.bank.models.Transaction;

/**
 * @author Leona
 *
 */

@Repository("transactionRepo")
public class TransactionRepository implements TransactionDAO {

	@Autowired
	JdbcTemplate jdbcTemplateObject;

	@Override
	public Boolean deposit(Double amount, Integer accNo) throws SQLException {
		String sql = "UPDATE bank_accounts SET accBalance = accBalance + ? WHERE accNo = ?";
		return jdbcTemplateObject.update(sql, amount, accNo) > 0;
	}

	@Override
	public List<Transaction> showTransactions() throws SQLException {
		String sql = "SELECT * FROM transactions";
		return jdbcTemplateObject.query(sql, new TransactionMapper());
	}

	@Override
	public Boolean withdraw(Double amount, Integer accNo) throws SQLException {
		String sql = "UPDATE bank_accounts SET accBalance = accBalance - ? WHERE accNo = ?";
		return jdbcTemplateObject.update(sql, amount, accNo) > 0;
	}

}
