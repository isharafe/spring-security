package com.ruchira.learn.springsecurity.auth;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import com.ruchira.learn.springsecurity.model.Role;
import com.ruchira.learn.springsecurity.model.User;

/**
 *
 * Spring works with Principles instead of Users.
 * So here creating a Principle from an User
 */
public class UserPrinciple implements UserDetails {

	private final User user;
	private final List<Role> roles;

	public UserPrinciple(@NonNull User user, @NonNull List<Role> roles) {
		this.user = user;
		this.roles = roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// using a set to make sure roles/authorities are unique
		return CollectionUtils.isEmpty(roles) ? Collections.emptySet() :
			roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
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
