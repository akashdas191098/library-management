package com.kane.library.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
	
	private Integer id;
	private String Name;
	
}
