package com.mt.ems.controller.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.mt.ems.controller.EmployeeController;
import com.mt.ems.exceptions.EmployeeNotFoundException;
import com.mt.ems.exceptions.NullCheckException;
import com.mt.ems.model.Employee;
import com.mt.ems.service.EmployeeService;

@SpringBootTest
public class TestEmployeeController {
	@Mock
	EmployeeService service;
	@InjectMocks
	EmployeeController controller;

	@Test
	public void testGetAllEmployees() {
		List<Employee> employees = Arrays.asList(new Employee(1, "varshini", (byte) 22, 30000.0, "hr"),
				new Employee(2, "sindhu", (byte) 23, 40000.0, "hr"));
		when(service.getAllEmployees()).thenReturn(employees);
		assertEquals(2, controller.getAllEmployees().size());
	}

	@Test
	public void testGetEmployeeById() throws EmployeeNotFoundException {
		when(service.getEmployeeById(1)).thenReturn(new Employee(1, "varshini", (byte) 22, 30000.0, "hr"));
		assertEquals(1, controller.getEmployeeById(1).getEmployeeId());
	}

	@Test
	public void testAddEmployee() throws NullPointerException, NullCheckException {
		Employee employee = new Employee(1, "varshini", (byte) 22, 30000.0, "hr");
		when(service.addEmployee(employee)).thenReturn(employee);
		assertEquals(1, controller.addEmployee(employee).getEmployeeId());
	}

	@Test
	public void testUpdateEmployeeById() throws EmployeeNotFoundException {
		Employee employee = new Employee("varshini", (byte) 23, 30000.0, "finance");
		Employee employeeById = new Employee(1, "varshini", (byte) 22, 30000.0, "hr");
		when(service.getEmployeeById(1)).thenReturn(employeeById);
		employee.setEmployeeId(employeeById.getEmployeeId());
		when(service.updateEmployee(employee)).thenReturn(employee);
		assertEquals(employee, controller.updateEmployeeById(1, employee));
	}

	@Test
	public void testDeleteEmployeeById() throws EmployeeNotFoundException {
		Employee employeeById = new Employee(1, "varshini", (byte) 22, 30000.0, "hr");
		when(service.getEmployeeById(1)).thenReturn(employeeById);
		doNothing().when(service).deleteEmployeeById(1);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		assertEquals(response, controller.deleteEmployeeById(1));
	}

	@Test
	public void testAddMultipleEmployees() throws NullPointerException, NullCheckException {
		List<Employee> employees = Arrays.asList(new Employee(1, "varshini", (byte) 22, 30000.0, "hr"),
				new Employee(2, "sindhu", (byte) 23, 40000.0, "hr"));
		when(service.addMultipleEmployees(employees)).thenReturn(employees);
		assertEquals(employees, controller.addMultipleEmployees(employees));
	}

}
