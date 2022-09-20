package com.ruchira.learn.springsecurity.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.authorizeHttpRequests(
				(authz) -> authz
				.antMatchers("/secured.html").authenticated()
				.anyRequest().permitAll()
					)
		.httpBasic();
		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		PasswordEncoder pswEncoder = passwordEncoder();
		List<UserDetails> users = new ArrayList<>();
		/**
		 * Need to enter authorities. o/w you will get an exception on startup complaining
		 * authorities are null
		 */
		users.add(User.builder().passwordEncoder(pswEncoder::encode).username("user").password("psw").authorities("USER", "DEV").build());
		users.add(User.builder().passwordEncoder(pswEncoder::encode).username("test").password("test").authorities("Admin").build());
		return new InMemoryUserDetailsManager(users);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
