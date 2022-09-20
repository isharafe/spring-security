package com.ruchira.learn.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
}
