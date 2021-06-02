package com.mt.ems.service.impl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.mt.ems.exceptions.EmployeeNotFoundException;
import com.mt.ems.exceptions.NullCheckException;
import com.mt.ems.model.Employee;
import com.mt.ems.repository.EmployeeRepository;
import com.mt.ems.service.impl.EmployeeServiceImpl;

@SpringBootTest
public class TestEmployeeServiceImpl {
	@Mock
	EmployeeRepository repo;
	@InjectMocks
	EmployeeServiceImpl service;

	@Test
	public void testGetAllEmployees() {
		List<Employee> employees = Arrays.asList(new Employee(1, "varshini", (byte) 22, 30000.0, "hr"),
				new Employee(2, "sindhu", (byte) 23, 40000.0, "hr"));
		when(repo.findAll()).thenReturn(employees);
		assertEquals(2, service.getAllEmployees().size());
	}

	@Test
	public void testAddEmployeeWithException() {
		Employee employee = new Employee(1, "varshini", (byte) 22, 30000.0, "");
		assertThrows(NullCheckException.class, () -> service.addEmployee(employee));
	}

	@Test
	public void testAddEmployee() throws NullPointerException, NullCheckException {
		Employee employee = new Employee(1, "varshini", (byte) 22, 30000.0, "hr");
		when(repo.save(employee)).thenReturn(employee);
		assertEquals(1, service.addEmployee(employee).getEmployeeId());
	}

	@Test
	public void testGetEmployeeById() throws EmployeeNotFoundException {
		when(repo.findById(1)).thenReturn(Optional.of(new Employee(1, "varshini", (byte) 22, 30000.0, "hr")));
		assertEquals("varshini", service.getEmployeeById(1).getEmployeeName());
	}

	@Test
	public void testGetEmployeeByIdWithException() {
		assertThrows(EmployeeNotFoundException.class, () -> service.getEmployeeById(0));
	}

	@Test
	public void testUpdateEmployee() {
		Employee employee = new Employee(1, "varshini", (byte) 22, 30000.0, "hr");
		when(repo.save(employee)).thenReturn(employee);
		assertEquals(1, service.updateEmployee(employee).getEmployeeId());
	}

	@Test
	public void testDeleteEmployeeById() {
		service.deleteEmployeeById(1);
		verify(repo, times(1)).deleteById(1);
	}

	@Test
	public void addMultipleEmployees() throws NullPointerException, NullCheckException {
		List<Employee> employees = Arrays.asList(new Employee(1, "varshini", (byte) 22, 30000.0, "hr"),
				new Employee(2, "sindhu", (byte) 23, 40000.0, "hr"));
		when(repo.saveAll(employees)).thenReturn(employees);
		assertEquals(employees, service.addMultipleEmployees(employees));
	}

	@Test
	public void addMultipleEmployeesWithException() {
		List<Employee> employees = Arrays.asList(new Employee(1, "varshini", (byte) 22, 30000.0, ""),
				new Employee(2, "sindhu", (byte) 23, 40000.0, "hr"));
		assertThrows(NullCheckException.class, () -> service.addMultipleEmployees(employees));
	}

	@Test
	public void addMultipleEmployeesWithExceptions() {
		List<Employee> employees = new ArrayList<>();
		assertThrows(NullCheckException.class, () -> service.addMultipleEmployees(employees));
	}

}
