package com.pessoal.bora.api.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true)
public class SecurityConfig {
	
	@Autowired
	private DataSource dataSource;

   
	
    @Bean
	public UserDetailsService userDetail() {
		
		/*String password = "{noop}password";
		
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

		return new InMemoryUserDetailsManager(driver, passenger, admin);*/
		
		
		var queryUsers = "select username, password, enabled from users where username=?";
        var queryRoles = "select u.username, r.roles from user_roles r, users u where r.user_id = u.id and u.username=?";

        var jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery(queryUsers);
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(queryRoles);
        return jdbcUserDetailsManager;
		             
	}

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {

		http
			.authorizeHttpRequests((auth) -> auth.requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll().anyRequest().authenticated())
			.httpBasic(Customizer.withDefaults())
			.csrf((csrf) -> csrf.disable())
			.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.headers(c1 -> c1.frameOptions(c2 -> c2.disable()));
		return http.build();

	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	

}
