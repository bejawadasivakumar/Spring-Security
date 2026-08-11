package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dto.UserRequest;
import com.example.demo.service.UserService;

@Controller
public class ViewController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public String user() {
		return "login";
	}
	
	@GetMapping("/dashboard")
	public String admin() {
		return "dashboard";
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
