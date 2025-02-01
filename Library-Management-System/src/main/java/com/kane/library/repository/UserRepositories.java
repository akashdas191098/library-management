package com.kane.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kane.library.entity.User;

public interface UserRepositories extends JpaRepository<User, Integer> {

}
