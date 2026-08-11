package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserRequest;
import com.example.demo.service.UserService;

@RestController
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/home")
	public String home() {
		return "Welcome Home";
	}
	
	@PostMapping("/register")
	public String register(@RequestBody UserRequest request) {
		userService.register(request);
		return "User added successfully..";
	}
	
	@GetMapping("/user")
	public String user() {
		return "Welcome User";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "Welcome Admin";
	}
}
/*
 Client
   |
   | username + password
   v
SecurityFilterChain
   |
   v
UsernamePasswordAuthenticationFilter
   |
   v
AuthenticationManager
   |
   v
DaoAuthenticationProvider
   |
   v
CustomUserDetailsService
   |
   v
UserRepository
   |
   v
MySQL Database
   |
   v
Returns User
   |
   v
PasswordEncoder.matches()
   |
   v
Authentication Successful
   |
   v
Controller Executes
*/
