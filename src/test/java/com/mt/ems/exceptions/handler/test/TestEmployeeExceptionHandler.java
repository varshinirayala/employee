package com.mt.ems.exceptions.handler.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mt.ems.exceptions.EmployeeNotFoundException;
import com.mt.ems.exceptions.NullCheckException;
import com.mt.ems.exceptions.handler.EmployeeExceptionHandler;

@SpringBootTest
public class TestEmployeeExceptionHandler {
	@Autowired
	EmployeeExceptionHandler ex;

	@Test
	public void testHandleEmployeeNotFoundException() {
		assertNotNull(ex.handleEmployeeNotFoundException(new EmployeeNotFoundException("not found")));
	}

	@Test
	public void testGlobalExcpetionHandler() {
		assertNotNull(ex.globalExcpetionHandler(new Exception()));
	}

	@Test
	public void testHandleNullCheckException() {
		assertNotNull(ex.handleNullCheckException(new NullCheckException("nulls are there")));
	}

	@Test
	public void testHandleNullPointerException() {
		assertNotNull(ex.handleNullPointerException());
	}

}
