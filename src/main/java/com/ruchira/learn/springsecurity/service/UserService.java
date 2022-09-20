package com.ruchira.learn.springsecurity.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ruchira.learn.springsecurity.auth.UserPrinciple;
import com.ruchira.learn.springsecurity.model.User;
import com.ruchira.learn.springsecurity.repo.UserRepository;

@Service
public class UserService implements UserDetailsService {

	private final UserRepository userRepo;

	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException(String.format("User: %s not found", username));
		}

		return new UserPrinciple(user);
	}
}
