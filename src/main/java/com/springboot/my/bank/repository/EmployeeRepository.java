package com.springboot.my.bank.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springboot.my.bank.dao.EmployeeDAO;
import com.springboot.my.bank.mappers.EmployeeMapper;
import com.springboot.my.bank.models.Employee;

@Repository("employeeRepo")
public class EmployeeRepository implements EmployeeDAO {
	@Autowired
	JdbcTemplate jdbcTemplateObject;

	@Override
	public boolean createEmployee(Employee emp) {
		String query = "INSERT INTO employees VALUES(?,?,?)";
		return jdbcTemplateObject.update(query, emp.getEmpId(), emp.getName(), emp.getBranchCode()) > 0 ? true : false;

	}

	@Override
	public boolean deleteEmployee(Integer empId) {
		String query = "DELETE FROM employees WHERE empId = ?";
		return jdbcTemplateObject.update(query, empId) > 0 ? true : false;

	}

	@SuppressWarnings("deprecation")
	@Override
	public Employee getEmployeeById(Integer empId) {
		String query = "SELECT * FROM employees WHERE empId=?";
		return jdbcTemplateObject.queryForObject(query, new Object[] { empId }, new EmployeeMapper());

	}

	@Override
	public List<Employee> showAllEmployees() {
		String query = "SELECT * FROM worker";
		return jdbcTemplateObject.query(query, new EmployeeMapper());
	}

	@Override
	public boolean updateEmployee(Integer empId, String name) {
		String query = "UPDATE employees SET name= ? WHERE empId= ? ";
		return jdbcTemplateObject.update(query, empId, name) > 0 ? true : false;

	}

}
