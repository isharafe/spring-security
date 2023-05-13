package my.learn.oidc.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import my.learn.oidc.service.UserService;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class Security {

	private final OAuthLoginSuccessHandler authLoginSuccessHandler;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		return http.authorizeHttpRequests(customizer ->
			customizer.anyRequest().authenticated()
		)
		.oauth2Login(customizer -> customizer
				.successHandler(authLoginSuccessHandler))
		.build();
	}

}
