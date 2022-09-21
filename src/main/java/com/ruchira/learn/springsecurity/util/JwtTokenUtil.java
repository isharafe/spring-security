package com.ruchira.learn.springsecurity.util;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;


@Component
public class JwtTokenUtil {

	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	@Value("${auth.jwt.secret}")
	private String secret;

	public DecodedJWT decode(String token) {
		Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
		JWTVerifier verifier = JWT.require(algorithm).build();
		return verifier.verify(token);
	}

	public String getAccessToken(String username, String requestUrl, Map<String, String> claims) {
		Date accessTokenExpireHours = new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000);

		Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
		Builder tokenBuilder = JWT.create()
				.withSubject(username)
				.withExpiresAt(accessTokenExpireHours)
				.withIssuer(requestUrl);
		claims.forEach((k,v)-> tokenBuilder.withClaim(k, v));
		return tokenBuilder.sign(algorithm);
	}

}
