package com.kane.library.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kane.library.constants.ApplicationConstants;
import com.kane.library.entity.User;
import com.kane.library.repository.UserRepositories;
import com.kane.library.request.UserRequest;
import com.kane.library.response.UserResponse;
import com.kane.library.services.UserServices;

@Service
public class UserServiceImpl implements UserServices {
	
	@Autowired
	UserRepositories repositories;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public UserResponse createUser(UserRequest userRequest) {
		User user = buildUser(userRequest);
		User savedUser =repositories.save(user);
		UserResponse userResponse = buildUserResponse(savedUser);
		return userResponse;
	}
	
	private UserResponse buildUserResponse(User savedUser) {
		return UserResponse.builder().id(savedUser.getId())
				.Name(savedUser.getFirstName()+ApplicationConstants.SPACE_FORMAT+savedUser.getLastName())
				.email(savedUser.getEmail())
				.build();
	}

	private User buildUser(UserRequest request) {
		return User.builder().firstName(request.getFirstName())
				.lastName(request.getLastName())
				.email(request.getEmail())
				.passWord(passwordEncoder.encode(request.getPassWord())).build();
	}
	
	

}
