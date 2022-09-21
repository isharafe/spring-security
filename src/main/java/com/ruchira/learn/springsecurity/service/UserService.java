package com.ruchira.learn.springsecurity.service;

import java.util.Collections;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// this is a dummy code
		// should get related user details from some repo using user name and make sure user is valid
		// check: https://github.com/isharafe/spring-security/blob/jdbc/src/main/java/com/ruchira/learn/springsecurity/service/UserService.java
		return new User(username, username, Collections.emptyList());
	}

}
