package com.dakshabhi.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.dakshabhi.demo.model.User;
import com.dakshabhi.demo.repository.UserRepository;

@Controller
public class UserController {
	@Autowired
	private UserRepository repo;

	@GetMapping("/home")
	public String viewHomePage()
	{
		return "index";
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
	    model.addAttribute("user", new User());
	     
	    return "signup_form";
	}
	
	
	@PostMapping("/process_register")
	public String saveUser(User user) {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodePassword= passwordEncoder.encode(user.getPassword());
		user.setPassword(encodePassword);
		
		repo.save(user);
		
		return "register_success";
	}
	
	@GetMapping("/users")
	public String listUsers(Model model) {
	    List<User> listUsers = repo.findAll();
	    model.addAttribute("listUsers", listUsers);
	     
	    return "users";
	}

}
