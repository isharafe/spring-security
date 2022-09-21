package com.ruchira.learn.springsecurity.controller;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ruchira.learn.springsecurity.util.JwtTokenUtil;

@RestController
public class AuthController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@PostMapping("/jwt/login")
	public String login(HttpServletRequest request, @RequestParam String username, @RequestParam String password) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);
		// implement authentication using auth manager
//		Authentication authenticate = authManager.authenticate(authenticationToken);

//		String accessToken = jwtTokenUtil.getAccessToken(
//				authenticate.getPrincipal().toString(), request.getRequestURI(), Collections.emptyMap());

		String accessToken = jwtTokenUtil.getAccessToken(
				username, request.getRequestURI(), Collections.emptyMap());
		return accessToken;
	}
}
