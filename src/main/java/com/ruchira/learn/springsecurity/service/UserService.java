package com.ruchira.learn.springsecurity.service;

import java.util.Collections;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ruchira.learn.springsecurity.auth.UserPrinciple;
import com.ruchira.learn.springsecurity.model.Role;
import com.ruchira.learn.springsecurity.model.User;
import com.ruchira.learn.springsecurity.repo.RoleRepository;
import com.ruchira.learn.springsecurity.repo.UserRepository;

@Service
public class UserService implements UserDetailsService {

	private final UserRepository userRepo;
	private final RoleRepository roleRepo;

	public UserService(UserRepository userRepo, RoleRepository roleRepo) {
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException(String.format("User: %s not found", username));
		}

		List<Role> roles = roleRepo.findByUsername(username);
		return new UserPrinciple(user, CollectionUtils.isEmpty(roles) ? Collections.emptyList() : roles);
	}
}
