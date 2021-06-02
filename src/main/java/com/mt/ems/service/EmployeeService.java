package com.mt.ems.service;

import java.util.List;

import com.mt.ems.exceptions.EmployeeNotFoundException;
import com.mt.ems.exceptions.NullCheckException;
import com.mt.ems.model.Employee;

public interface EmployeeService {

	Employee addEmployee(Employee employee) throws NullCheckException;

	List<Employee> getAllEmployees();

	Employee getEmployeeById(int employeeId) throws EmployeeNotFoundException;

	Employee updateEmployee(Employee employee);

	void deleteEmployeeById(int employeeId);

	List<Employee> addMultipleEmployees(List<Employee> employees) throws NullCheckException;

}
