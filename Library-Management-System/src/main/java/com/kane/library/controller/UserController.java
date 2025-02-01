package com.kane.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kane.library.request.UserRequest;
import com.kane.library.response.UserResponse;
import com.kane.library.services.UserServices;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserServices userServices;
	
	
	@PostMapping("/createusers")
	public UserResponse createUser(@RequestBody UserRequest userRequest) {
		
		return userServices.createUser(userRequest);
	}
	

}
