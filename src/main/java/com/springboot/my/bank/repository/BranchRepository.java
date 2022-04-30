
package com.springboot.my.bank.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springboot.my.bank.dao.BranchDAO;
import com.springboot.my.bank.mappers.BranchMapper;
import com.springboot.my.bank.mappers.CustomerMapper;
import com.springboot.my.bank.mappers.TransactionMapper;
import com.springboot.my.bank.models.Branch;
import com.springboot.my.bank.models.Customer;
import com.springboot.my.bank.models.Transaction;

@Repository("branchRepo")
public class BranchRepository implements BranchDAO {

	@Autowired
	JdbcTemplate jdbcTemplateObject;

	@Override
	public Boolean createBranch(Branch branch) throws SQLException {
		String sql = "INSERT INTO branches VALUES (?, ?, ?, ?)";

		try {
			jdbcTemplateObject.update(sql, branch.getBranchCode(), branch.getAddress(), branch.getManager(),
					branch.getBankCode());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Branch viewDetailsByIFSC(String ifscCode) throws SQLException {
		String sql = "SELECT * FROM branches WHERE branchCode = ?";
		return jdbcTemplateObject.queryForObject(sql, new Object[] { ifscCode }, new BranchMapper());
	}

	@Override
	public List<Transaction> showTransactions() throws SQLException {
		String sql = "SELECT * FROM transactions";
		return jdbcTemplateObject.query(sql, new TransactionMapper());
	}

	@Override
	public List<Customer> showCustomers() throws SQLException {
		String sql = "SELECT * FROM customers";
		return jdbcTemplateObject.query(sql, new CustomerMapper());
	}

	@Override
	public List<Branch> showBranches() throws SQLException {
		String sql = "SELECT * FROM branches";
		return jdbcTemplateObject.query(sql, new BranchMapper());
	}

	@Override
	public Boolean deleteByIFSC(String ifscCode) throws SQLException {
		String sql = "DELETE FROM branches WHERE branchCode = ?";
		return jdbcTemplateObject.update(sql, ifscCode) > 0;
	}

	@Override
	public Boolean updateBranchManager(Branch branch) throws SQLException {
		String sql = "UPDATE branches SET address=?, manager=?, bankCode=?, isHeadOffice=? WHERE branchCode= ? ";
		return jdbcTemplateObject.update(sql, branch.getAddress().toString(), branch.getManager(), branch.getBankCode(),
				branch.getHeadOffice(), branch.getBranchCode()) > 0;
	}

}
