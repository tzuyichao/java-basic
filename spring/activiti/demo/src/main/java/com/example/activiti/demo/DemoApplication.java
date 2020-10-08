package com.example.activiti.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public UserDetailsManager userDetailsManager() {
		InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();

		inMemoryUserDetailsManager.createUser(makeSimpleUserWithSingleRole("admin", "admin", "ROLE_ACTIVITI_ADMIN"));
		inMemoryUserDetailsManager.createUser(makeSimpleUserWithSingleRole("user1", "user1", "ROLE_ACTIVITI_USER"));

		return inMemoryUserDetailsManager;
	}

	private UserDetails makeSimpleUserWithSingleRole(String username, String password, String role) {
		List<String> authoritiesStrings = Arrays.asList(role);
		List<GrantedAuthority> authorities = authoritiesStrings.stream().map(str -> new SimpleGrantedAuthority(str)).collect(Collectors.toList());
		User user = new User(username, passwordEncoder().encode(password), authorities);
		return user;
	}

}
