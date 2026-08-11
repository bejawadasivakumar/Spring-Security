package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	// Password
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// Authentication
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails admin = User.builder()
				                 .username("kumar")
				                 .password(passwordEncoder().encode("kumar123"))
				                 .roles("ADMIN")
				                 .build();
		UserDetails user = User.builder()
				               .username("siva")
				               .password(passwordEncoder().encode("siva123"))
				               .roles("USER")
				               .build();
		return new InMemoryUserDetailsManager(admin,user);
	}
	
	// Authorization
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		return http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/home").permitAll()
						.requestMatchers("/user").hasAnyRole("USER","ADMIN")
						.requestMatchers("/admin").hasRole("ADMIN")
						.anyRequest().authenticated()
						)
			   .httpBasic(Customizer.withDefaults())
			   .build();
	}

}
