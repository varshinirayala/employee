package com.mt.ems.repository.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mt.ems.model.Employee;
import com.mt.ems.repository.EmployeeRepository;

@SpringBootTest
public class TestEmployeeRepository {
	@Autowired
	EmployeeRepository repo;

	@Test
	public void testSave() {
		Employee employee = new Employee(1, "varshini", (byte) 22, 30000.0, "hr");
		repo.save(employee);
		assertNotNull(repo.findById(1));
	}

	@Test
	public void testFindAll() {
		// List<Employee> employees = repo.findAll();
		assertEquals(8, repo.findAll().size());
	}

}
