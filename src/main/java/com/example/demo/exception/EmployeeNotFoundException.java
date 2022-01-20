package com.example.demo.exception;

@SuppressWarnings("serial")
public class EmployeeNotFoundException extends Exception{
	public EmployeeNotFoundException(String errorResponse) {
		super(errorResponse);
	}
}
