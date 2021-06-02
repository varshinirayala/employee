package com.mt.ems.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employees")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int employeeId;
	private String employeeName;
	@NotNull
	private byte employeeAge;
	@NotNull
	private double salary;
	private String department;

	public Employee() {
		super();
	}

	public Employee(int employeeId, String employeeName, byte employeeAge, double salary, String department) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeAge = employeeAge;
		this.salary = salary;
		this.department = department;
	}

	public Employee(String employeeName, byte employeeAge, double salary, String department) {
		super();
		this.employeeName = employeeName;
		this.employeeAge = employeeAge;
		this.salary = salary;
		this.department = department;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public byte getEmployeeAge() {
		return employeeAge;
	}

	public double getSalary() {
		return salary;
	}

	public String getDepartment() {
		return department;
	}

}
