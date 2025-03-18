package com.kane.library.user.test.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kane.library.entity.User;
import com.kane.library.repository.UserRepositories;
import com.kane.library.request.UserRequest;
import com.kane.library.response.UserResponse;
import com.kane.library.services.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class UserServicetest {
	
	@InjectMocks
	private UserServiceImpl userService;
	
	@Mock
	private UserRepositories userRepo;
	
	@Test
	public void createUserTest() {
		when(userRepo.save(createUser())).thenReturn(createUser());
		UserResponse userResponse = userService.createUser(createUserRequest());
		assertNotNull(userResponse);
	}
	
	private User createUser() {
		User user = new User();
		return user;
	}
	
	private UserRequest createUserRequest() {
		UserRequest userRequest = new UserRequest();
		userRequest.setFirstName("test");
		userRequest.setLastName("test");
		userRequest.setPassWord("test");
		return userRequest;
	}
	

}
