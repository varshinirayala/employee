package com.mt.ems.exceptions.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mt.ems.exceptions.EmployeeNotFoundException;
import com.mt.ems.exceptions.NullCheckException;
import com.mt.ems.exceptions.errormessage.ErrorMessage;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class EmployeeExceptionHandler {
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<?> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
		log.error("EmployeeNotFoundException occurred");
		ErrorMessage errorDetails = new ErrorMessage(404, ex.getMessage(), new Date());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalExcpetionHandler(Exception ex) {
		log.error("Exception occurred");
		ErrorMessage errorDetails = new ErrorMessage(500, ex.getMessage(), new Date());
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NullCheckException.class)
	public ResponseEntity<?> handleNullCheckException(NullCheckException ex) {
		ErrorMessage errorDetails = new ErrorMessage(500, ex.getMessage(), new Date());
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ErrorMessage> handleNullPointerException() {
		log.error("NullPointerException occurred");
		ErrorMessage error = new ErrorMessage(500, "The fields cannot be empty", new Date());
		return new ResponseEntity<ErrorMessage>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
