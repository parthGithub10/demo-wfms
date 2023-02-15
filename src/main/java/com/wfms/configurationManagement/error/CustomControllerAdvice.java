package com.wfms.configurationManagement.error;

import com.wfms.configurationManagement.exceptions.CustomDataNotFoundException;
import com.wfms.configurationManagement.exceptions.CustomErrorException;
import com.wfms.configurationManagement.exceptions.CustomInternalServerException;
import com.wfms.configurationManagement.exceptions.CustomBadRequestException;
import com.wfms.configurationManagement.exceptions.CustomConflictException;
import com.wfms.configurationManagement.exceptions.CustomAuthException;
import com.wfms.configurationManagement.exceptions.CustomForbiddenException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
class CustomControllerAdvice {
    @ExceptionHandler(CustomDataNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomDataNotFoundExceptions(
            Exception e
    ) {
        HttpStatus status = HttpStatus.NOT_FOUND; // 404

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        String stackTrace = stringWriter.toString();

        return new ResponseEntity<>(
                new ErrorResponse(
                        status,
                        e.getMessage(),
                        stackTrace
                ),
                status
        );
    }

    @ExceptionHandler(CustomBadRequestException.class)
    public ResponseEntity<ErrorResponse> handleCustomParameterConstraintExceptions(
            Exception e
    ) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400

        return new ResponseEntity<>(
                new ErrorResponse(
                        status,
                        e.getMessage()
                ),
                status
        );
    }
    
    @ExceptionHandler(CustomAuthException.class)
    public ResponseEntity<ErrorResponse> handleCustomAuthExceptions(
            Exception e
    ) {
        HttpStatus status = HttpStatus.UNAUTHORIZED; // 401

        return new ResponseEntity<>(
                new ErrorResponse(
                        status,
                        e.getMessage()
                ),
                status
        );
    }
    
    @ExceptionHandler(CustomForbiddenException.class)
    public ResponseEntity<ErrorResponse> handleCustomForbiddenExceptions(
            Exception e
    ) {
        HttpStatus status = HttpStatus.FORBIDDEN; // 403

        return new ResponseEntity<>(
                new ErrorResponse(
                        status,
                        e.getMessage()
                ),
                status
        );
    }
    

    @ExceptionHandler(CustomErrorException.class)
    public ResponseEntity<ErrorResponse> handleCustomErrorExceptions(
            Exception e
    ) {
        CustomErrorException customErrorException = (CustomErrorException) e;

        HttpStatus status = customErrorException.getStatus();

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        customErrorException.printStackTrace(printWriter);
        String stackTrace = stringWriter.toString();

        return new ResponseEntity<>(
                new ErrorResponse(
                        status,
                        customErrorException.getMessage(),
                        stackTrace,
                        customErrorException.getData()
                ),
                status
        );
    }
    
    @ExceptionHandler(CustomConflictException.class)
    public ResponseEntity<ErrorResponse> handleCustomConflictExceptions(
            Exception e
    ) {
    	  HttpStatus status = HttpStatus.CONFLICT; // 409

          return new ResponseEntity<>(
                  new ErrorResponse(
                          status,
                          e.getMessage()
                  ),
                  status
          );
    }
    

    @ExceptionHandler(CustomInternalServerException.class) // exception handled
    public ResponseEntity handleExceptions(
            Exception e
    ) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 500

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        String stackTrace = stringWriter.toString();

        return new ResponseEntity<>(
                new ErrorResponse(
                        status,
                        e.getMessage(),
                        stackTrace // specifying the stack trace in case of 500
                ),
                status
        );
    }
}
