
package com.springboot.my.bank.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springboot.my.bank.dao.CustomerDAO;
import com.springboot.my.bank.mappers.CustomerMapper;
import com.springboot.my.bank.mappers.TransactionMapper;
import com.springboot.my.bank.models.Customer;
import com.springboot.my.bank.models.Transaction;

@Repository("customerRepo")
public class CustomerRepository implements CustomerDAO {
	@Autowired
	JdbcTemplate jdbcTemplateObject;

	public Boolean createCustomer(Customer customer) {
		String query = "INSERT INTO customers VALUES(?,?,?)";
		return jdbcTemplateObject.update(query, customer.getCustomerId(), customer.getName(), customer.getAddress()) > 0
				? true
				: false;

	}

	public Boolean deleteCustomerById(Integer customerId) throws SQLException {
		String query = "DELETE FROM customers WHERE customerId = ?";
		return jdbcTemplateObject.update(query, customerId) > 0 ? true : false;

	}

	@SuppressWarnings("deprecation")
	public Customer getCustomerByAccNo(Integer accNo) throws SQLException {
		String query = "SELECT customerId,name,address FROM customers INNER JOIN bank_accounts ON customerId=customer WHERE accNo=?;";
		return jdbcTemplateObject.queryForObject(query, new Object[] { accNo }, new CustomerMapper());

	}

	@SuppressWarnings("deprecation")
	public Customer getCustomerById(Integer customerId) throws SQLException {
		String query = "SELECT * FROM customers WHERE customerId=?";
		return jdbcTemplateObject.queryForObject(query, new Object[] { customerId }, new CustomerMapper());
	}

	public Boolean updateCustomerById(Integer customerId, String name, String address) throws SQLException {
		String query = "UPDATE customers SET name=?,address=? WHERE customerId= ? ";
		return jdbcTemplateObject.update(query, name, address, customerId) > 0 ? true : false;

	}

	@SuppressWarnings("deprecation")
	public List<Transaction> showTransactions(Integer customerId, String accType) {
		String query = "select * from transactions where accNo=(select accNo from bank_accounts where customer=? and type=?)";
		return jdbcTemplateObject.query(query, new Object[] { customerId, accType }, new TransactionMapper());
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Customer> getCustomerByBranches(String branchCode) throws SQLException {
		String sql = "select customerId,name,address from customers inner join bank_accounts on customerId=customer where branch= ?";
		return jdbcTemplateObject.query(sql, new Object[] { branchCode }, new CustomerMapper());
	}

}
