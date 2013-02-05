package experiments.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.social.connect.UserProfile;

public class OAuthAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		OAuthAuthenticationToken token = null;
		if (authentication instanceof OAuthAuthenticationToken) {
			OAuthAuthenticationToken preAuthenticationToken = (OAuthAuthenticationToken) authentication;
			token = new OAuthAuthenticationToken(
					preAuthenticationToken.getConnectionKey(),
					(UserProfile) preAuthenticationToken.getPrincipal());
		}
		return token;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return OAuthAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
