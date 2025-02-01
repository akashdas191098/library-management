package com.kane.library.exceptionHandeling;

public class APIException extends RuntimeException  {
    String message;
	public APIException(String message) {
		super(String.format(message));
		this.message = message;
	}
    
}
