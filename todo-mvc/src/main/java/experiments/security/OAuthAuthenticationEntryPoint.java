package experiments.security;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.web.ConnectSupport;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.ServletWebRequest;

public class OAuthAuthenticationEntryPoint<T> implements
		AuthenticationEntryPoint {

	public static final String ORIGINAL_URL = "ORIGINAL_URL";

	private final Class<T> clazz;

	@Resource
	private ConnectionFactoryLocator connectionFactoryLocator;

	private final ConnectSupport connectSupport = new ConnectSupport();

	public OAuthAuthenticationEntryPoint(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public void commence(final HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		ConnectionFactory<T> connectionFactory = connectionFactoryLocator
				.getConnectionFactory(this.clazz);
		saveRequestUrlInSession(request);
		HttpServletRequest wrapRequest = wrapRequest(request);

		MultiValueMap<String, String> parameters = null;
		String authUrl = connectSupport.buildOAuthUrl(connectionFactory,
				new ServletWebRequest(wrapRequest), parameters);
		response.sendRedirect(authUrl);
	}

	protected MultiValueMap<String, String> buildParameters(
			HttpServletRequest request) {
		return null;
	}

	protected HttpServletRequest wrapRequest(final HttpServletRequest request) {
		HttpServletRequest wrapRequest = new HttpServletRequestWrapper(request) {
			@Override
			public StringBuffer getRequestURL() {
				StringBuffer requestURL = super.getRequestURL();
				String contextPath = request.getContextPath() + "/";
				int indexOfServletPath = requestURL.indexOf(contextPath);
				return requestURL.delete(
						indexOfServletPath + contextPath.length(),
						requestURL.length());
			}
		};
		return wrapRequest;
	}

	private void saveRequestUrlInSession(HttpServletRequest request) {
		request.getSession().setAttribute(ORIGINAL_URL,
				request.getRequestURL().toString());
	}

}
