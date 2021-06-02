package com.mt.ems.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mt.ems.exceptions.EmployeeNotFoundException;
import com.mt.ems.exceptions.NullCheckException;
import com.mt.ems.model.Employee;
import com.mt.ems.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/addEmployee")
	public Employee addEmployee(@Valid @RequestBody Employee employee) throws NullCheckException, NullPointerException {
		return employeeService.addEmployee(employee);
	}

	@PostMapping("/addEmployees")
	public List<Employee> addMultipleEmployees(@Valid @RequestBody List<Employee> employees)
			throws NullCheckException, NullPointerException {
		return employeeService.addMultipleEmployees(employees);
	}

	@GetMapping("/getEmployees")
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	@GetMapping("/getEmployeeById/{id}")
	public Employee getEmployeeById(@PathVariable(value = "id") int employeeId) throws EmployeeNotFoundException {
		return employeeService.getEmployeeById(employeeId);
	}

	@PutMapping("/updateEmployee/{id}")
	public Employee updateEmployeeById(@PathVariable(value = "id") int employeeId, @RequestBody Employee employee)
			throws EmployeeNotFoundException {
		Employee employeeById = employeeService.getEmployeeById(employeeId);
		employee.setEmployeeId(employeeById.getEmployeeId());
		Employee updatedEmployee = employeeService.updateEmployee(employee);
		log.info("Updated successfully");
		return updatedEmployee;
	}

	@DeleteMapping("/deleteEmployee/{id}")
	public Map<String, Boolean> deleteEmployeeById(@PathVariable(value = "id") int employeeId)
			throws EmployeeNotFoundException {
		employeeService.getEmployeeById(employeeId);
		employeeService.deleteEmployeeById(employeeId);
		log.info("Deleted successfully");
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
