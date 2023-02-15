package com.wfms.configurationManagement.exceptions;

public class CustomConflictException extends RuntimeException {
	public CustomConflictException() {
		super();
	}

	public CustomConflictException(String message) {
		super(message);
	}

	public CustomConflictException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
