package com.wfms.configurationManagement.exceptions;

public class CustomDataNotFoundException extends RuntimeException {
    public CustomDataNotFoundException() {
        super();
    }

    public CustomDataNotFoundException(String message) {
        super(message);
    }
    
    public CustomDataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}