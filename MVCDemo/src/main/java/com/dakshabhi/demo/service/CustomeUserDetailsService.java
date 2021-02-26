package com.dakshabhi.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.dakshabhi.demo.model.User;
import com.dakshabhi.demo.repository.UserRepository;

public class CustomeUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository repo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user =repo.findByEmail(username);
		if(user==null)
		{
			throw new UsernameNotFoundException("User not found");
		}
		
		return new CustomeUserDetails(user);
	}

}
