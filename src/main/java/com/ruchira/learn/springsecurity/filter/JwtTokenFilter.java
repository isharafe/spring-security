package com.ruchira.learn.springsecurity.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.ruchira.learn.springsecurity.service.UserService;
import com.ruchira.learn.springsecurity.util.JwtTokenUtil;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

	private static final String BEARER_PREFIX = "Bearer ";

	private final JwtTokenUtil jwtTokenUtil;
	private final UserService userService;

	public JwtTokenFilter(JwtTokenUtil jwtTokenUtil, UserService userService) {
		this.jwtTokenUtil = jwtTokenUtil;
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		// Get authorization header and validate
		final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (header == null || !header.startsWith(BEARER_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}

		try {
			// Get jwt token and validate
			final String token = header.split(" ")[1].trim();
			DecodedJWT decodedJWT = jwtTokenUtil.decode(token);
			String username = decodedJWT.getSubject();
			UserDetails principle = userService.loadUserByUsername(username);

			if(principle == null) {
				throw new UsernameNotFoundException("Invalid user : " + username);
			}

			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(principle, null, principle.getAuthorities());
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authentication);

			chain.doFilter(request, response);
		} catch (Throwable t) {
			// jwt decode will fail for invalid and expired tokens
			response.setHeader("error", t.getMessage());
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
		}

	}

}
