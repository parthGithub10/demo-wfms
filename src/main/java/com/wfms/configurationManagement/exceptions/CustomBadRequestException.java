package com.wfms.configurationManagement.exceptions;

public class CustomBadRequestException extends RuntimeException {
    public CustomBadRequestException() {
        super();
    }

    public CustomBadRequestException(String message) {
        super(message);
    }
    
    public CustomBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}