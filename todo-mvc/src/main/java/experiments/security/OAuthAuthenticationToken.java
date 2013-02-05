package experiments.security;

import java.util.Arrays;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.UserProfile;

public class OAuthAuthenticationToken extends AbstractAuthenticationToken {

	private final ConnectionKey connectionKey;

	private final UserProfile userProfile;

	public OAuthAuthenticationToken(ConnectionKey connectionKey,
			UserProfile userProfile) {
		super(Arrays.asList(new SimpleGrantedAuthority("USER")));
		this.connectionKey = connectionKey;
		this.userProfile = userProfile;
	}

	private static final long serialVersionUID = 1L;

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return userProfile;
	}

	/**
	 * @return the connection
	 */
	protected ConnectionKey getConnectionKey() {
		return connectionKey;
	}

	@Override
	public String getName() {
		return connectionKey.toString();
	}

}
