package com.mt.ems.exceptions.errormessage;

import java.util.Date;

import lombok.Getter;

@Getter
public class ErrorMessage {
	private int errorCode;
	private String errorDescription;
	private Date date;

	public ErrorMessage(int errorCode, String errorDescription, Date date) {
		super();
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
		this.date = date;
	}

}
