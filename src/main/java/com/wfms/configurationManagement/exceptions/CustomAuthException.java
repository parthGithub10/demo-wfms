package com.wfms.configurationManagement.exceptions;

public class CustomAuthException extends RuntimeException {;

	public CustomAuthException() {
		super();
	}

	public CustomAuthException(String message) {
		super(message);
	}
	
	public CustomAuthException(String message, Throwable cause) {
		super(message, cause);
	}
}
