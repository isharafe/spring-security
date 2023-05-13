package my.learn.oidc.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import my.learn.oidc.service.UserService;

@Component
@AllArgsConstructor
public class OAuthLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private final UserService userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {

		OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
		DefaultOidcUser oidcUser = (DefaultOidcUser) oauthToken.getPrincipal();
		userService.processOAuthPostLogin(
				oidcUser.getEmail(),
				oidcUser.getEmail(),
				oauthToken.getAuthorizedClientRegistrationId());

		super.onAuthenticationSuccess(request, response, authentication);
	}
}
