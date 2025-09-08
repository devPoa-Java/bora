package com.pessoal.bora.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

   
	
	@Bean
	public UserDetailsService userDetail() throws Exception {
		
		String password = "{noop}password";
		
		UserDetails driver = User.builder()
				.username("driver")
				.password(password)
				.roles("DRIVER")
				.build();
		
		UserDetails passenger = User.builder()
				.username("passenger")
				.password(password)
				.roles("PASSENGER")
				.build();
		UserDetails admin = User.builder()
				.username("admin")
				.password(password)
				.roles("ADMIN")
				.build();

		return new InMemoryUserDetailsManager(driver, passenger, admin);
		             
	}

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {

		http
			.authorizeHttpRequests((auth) -> auth.anyRequest().authenticated())
			.httpBasic(Customizer.withDefaults())
			.csrf((csrf) -> csrf.disable())
			.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		return http.build();

	}
	
	
	
	

}
