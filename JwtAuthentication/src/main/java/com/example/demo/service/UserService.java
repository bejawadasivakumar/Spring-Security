package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserRequestDto;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	public void register(UserRequestDto request) {
		User user = new User();
		user.setUsername(request.getUsername());
	    user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRole(request.getRole());
		userRepository.save(user);
	}

	public String loginVerify(UserRequestDto request) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));
		if(authentication.isAuthenticated()) {
			return jwtService.generateToken(request.getUsername());
		}
		return "Invalid user request!";
	}
}
