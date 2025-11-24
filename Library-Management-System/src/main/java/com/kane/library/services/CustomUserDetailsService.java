package com.kane.library.services;

import com.kane.library.entity.User;
import com.kane.library.exceptionHandeling.APIException;
import com.kane.library.repository.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepositories userRepositories;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepositories.findByEmail(username).orElseThrow(()-> new APIException("User Invalid"));

        return user;
    }
}
