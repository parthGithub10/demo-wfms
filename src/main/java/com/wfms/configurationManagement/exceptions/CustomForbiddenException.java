package com.wfms.configurationManagement.exceptions;

public class CustomForbiddenException extends RuntimeException{

	public CustomForbiddenException() {
		super();
	}

	public CustomForbiddenException(String message) {
		super(message);
	}
	
	public CustomForbiddenException(String message, Throwable cause) {
		super(message, cause);
	}

}
