package com.ruchira.learn.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ruchira.learn.springsecurity.model.User;
import com.ruchira.learn.springsecurity.repo.UserRepository;

@Component
public class BootstrapUsers implements CommandLineRunner {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	PasswordEncoder encoder;

	@Override
	public void run(String... args) throws Exception {
		User test = userRepo.findByUsername("test");
		if (test == null) {
			test = new User();
			test.setUsername("test");
			test.setPassword(encoder.encode("test"));
			userRepo.save(test);
		}
	}
}
