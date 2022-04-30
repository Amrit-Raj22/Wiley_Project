/**
 * 
 */
package com.springboot.my.bank.dao;

import java.sql.SQLException;
import java.util.List;

import com.springboot.my.bank.models.Employee;

public interface EmployeeDAO {
public List<Employee> showAllEmployees(); 
public Employee getEmployeeById(Integer employeeId);
public boolean updateEmployee(Integer employeeId,String employeeName);
public boolean deleteEmployee(Integer employeeId);
public boolean createEmployee(Employee emp) ;

}
