package com.kane.library.services;

import com.kane.library.request.UserRequest;
import com.kane.library.response.UserResponse;

public interface UserServices {

	
	public UserResponse createUser(UserRequest userRequest);
}
