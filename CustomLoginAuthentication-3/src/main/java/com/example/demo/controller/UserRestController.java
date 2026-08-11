package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserRequest;
import com.example.demo.service.UserService;

@RestController
public class UserRestController {

	    @Autowired
	    private UserService userService;

	    @PostMapping("/register")
	    public String register(@RequestBody UserRequest request) {
	        userService.register(request);
	        return "User added successfully";
	    }

}
