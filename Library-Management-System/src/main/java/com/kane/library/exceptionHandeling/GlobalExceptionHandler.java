package com.kane.library.exceptionHandeling;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.kane.library.constants.ErrorConstants;

@ComponentScan(basePackages = "com.kane.library")
@RestControllerAdvice(basePackages = "com.kane.library")
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(APIException.class)
	public ResponseEntity<ErrorInfo> globalExceptionHandler(APIException ex){
		ErrorInfo apiResponse = new ErrorInfo(ErrorConstants.status,ex.getMessage());
		
		return new ResponseEntity<ErrorInfo>(apiResponse, HttpStatus.NOT_FOUND);
	}
}
