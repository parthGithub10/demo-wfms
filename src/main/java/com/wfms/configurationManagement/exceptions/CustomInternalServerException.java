package com.wfms.configurationManagement.exceptions;

public class CustomInternalServerException extends RuntimeException {
	public CustomInternalServerException() {
		super();
	}

	public CustomInternalServerException(String message) {
		super(message);
	}

	public CustomInternalServerException(String message, Throwable cause) {
		super(message, cause);
	}
}
