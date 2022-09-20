package com.ruchira.learn.springsecurity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.ruchira.learn.springsecurity.model.Role;
import com.ruchira.learn.springsecurity.model.User;
import com.ruchira.learn.springsecurity.repo.RoleRepository;
import com.ruchira.learn.springsecurity.repo.UserRepository;

@Component
public class BootstrapUsers implements CommandLineRunner {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RoleRepository roleRepo;

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

		User abcd = userRepo.findByUsername("abcd");
		if (abcd == null) {
			abcd = new User();
			abcd.setUsername("abcd");
			abcd.setPassword(encoder.encode("abcd"));
			userRepo.save(abcd);
		}

		List<Role> roles = roleRepo.findByUsername("abcd");
		if(CollectionUtils.isEmpty(roles)) {
			Role r1 = new Role();
			r1.setUsername("abcd");
			r1.setName("User");
			roleRepo.save(r1);
		}
	}
}
