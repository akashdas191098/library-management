package com.kane.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {

		UserResponse userResponse = userServices.createUser(userRequest);
		
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
	}
	

}
