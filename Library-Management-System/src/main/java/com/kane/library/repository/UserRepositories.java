package com.kane.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kane.library.entity.User;

@Repository
public interface UserRepositories extends JpaRepository<User, Integer> {

}
