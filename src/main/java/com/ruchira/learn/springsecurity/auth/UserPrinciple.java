package com.ruchira.learn.springsecurity.auth;

import java.util.Collection;
import java.util.Collections;

import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ruchira.learn.springsecurity.model.User;

/**
 *
 * Spring works with Principles instead of Users.
 * So here creating a Principle from an User
 */
public class UserPrinciple implements UserDetails {

	private final User user;

	public UserPrinciple(@NonNull User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptyList();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
