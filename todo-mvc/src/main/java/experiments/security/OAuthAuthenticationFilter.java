package experiments.security;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.connect.web.ConnectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.GenericFilterBean;

public class OAuthAuthenticationFilter<T> extends GenericFilterBean {

	private final Class<T> clazz;

	@Resource
	private ConnectionFactoryLocator connectionFactoryLocator;

	private final ConnectSupport connectSupport = new ConnectSupport();

	private final AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource = new WebAuthenticationDetailsSource();
	private AuthenticationManager authenticationManager;
	private final AuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();

	public OAuthAuthenticationFilter(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (request instanceof HttpServletRequest
				&& response instanceof HttpServletResponse) {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			Authentication authentication = SecurityContextHolder.getContext()
					.getAuthentication();
			boolean callbackRequest = isCallbackRequest(httpRequest);
			logger.debug("Authentication: " + authentication);
			if (authentication == null && callbackRequest) {
				try {
					handleCallback(httpRequest, httpResponse);
					String originalUrl = httpRequest
							.getSession()
							.getAttribute(
									OAuthAuthenticationEntryPoint.ORIGINAL_URL)
							.toString();
					if (StringUtils.hasLength(originalUrl)) {
						httpRequest.getSession().removeAttribute(
								OAuthAuthenticationEntryPoint.ORIGINAL_URL);
						httpResponse.sendRedirect(originalUrl);
					} else {
						logger.debug("no information about original requested url");
						chain.doFilter(httpRequest, httpResponse);
					}
				} catch (AuthenticationException e) {
					failureHandler.onAuthenticationFailure(httpRequest,
							httpResponse, e);
				}
			} else {
				chain.doFilter(httpRequest, response);
			}
		} else {
			throw new ServletException("Only http objects are supported");
		}
	}

	private boolean isCallbackRequest(HttpServletRequest httpRequest) {
		return httpRequest.getParameter("code") != null
				&& httpRequest.getMethod().equalsIgnoreCase(
						RequestMethod.GET.name());
	}

	private void handleCallback(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) throws IOException {
		logger.debug("Handle callback after weibo authentication");
		OAuth2ConnectionFactory<?> connectionFactory = (OAuth2ConnectionFactory<?>) connectionFactoryLocator
				.getConnectionFactory(this.clazz);
		if (connectionFactory != null) {
			ServletWebRequest request = new ServletWebRequest(httpRequest);
			Connection<?> connection = connectSupport.completeConnection(
					connectionFactory, request);

			// User has returned after authenticating through Social network web
			// site. Need to authenticate to Spring Security.
			UserProfile userProfile = connection.fetchUserProfile();
			OAuthAuthenticationToken token = new OAuthAuthenticationToken(
					connection.getKey(), userProfile);
			token.setDetails(authenticationDetailsSource
					.buildDetails(httpRequest));

			Authentication authentication = getAuthenticationManager()
					.authenticate(token);
			// Setup the security context
			SecurityContextHolder.getContext()
					.setAuthentication(authentication);
		} else {
			logger.warn("Unable to find appropriate connection factory for "
					+ this.clazz);
		}
	}

	/**
	 * @return the authenticationManager
	 */
	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	/**
	 * @param authenticationManager
	 *            the authenticationManager to set
	 */
	public void setAuthenticationManager(
			AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

}
