package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.UserRequestDto;
import com.example.demo.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody UserRequestDto request) {
        userService.register(request);
        return "User added successfully";
    }
    @PostMapping("/login")
    public String login(@RequestBody UserRequestDto request) {
    	String token = userService.loginVerify(request);
    	//change the return type
    	return token;
    }
    @GetMapping("/hello")
    public String hello() {
    	return "hello";
    }
    
}
