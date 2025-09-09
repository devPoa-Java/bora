package com.pessoal.bora.api.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.pessoal.bora.api.domain.User;
import com.pessoal.bora.api.repositories.UserRepository;

import jakarta.annotation.PostConstruct;

@Configuration
public class LoadUserConfig {
	
	@Autowired
    PasswordEncoder passwordEncoder;
	
	@Autowired
	UserRepository userRepository;
	
	@PostConstruct
	public void init() {
		User admin = new User();
		admin.setPassword(passwordEncoder.encode("password"));
		admin.setRoles(List.of("ROLE_ADMIN"));
		admin.setUsername("admin");;
		admin.setEnabled(true);
		
		userRepository.save(admin);
		
		
	}

}
