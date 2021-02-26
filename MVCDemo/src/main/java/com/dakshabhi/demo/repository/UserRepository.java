package com.dakshabhi.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dakshabhi.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	
	@Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);
}
