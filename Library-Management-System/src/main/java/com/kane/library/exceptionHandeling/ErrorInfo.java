package com.kane.library.exceptionHandeling;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorInfo {
	
	private boolean code;
	private String message;	
	
	
}
