/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package experiments.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.weibo.connect.WeiboConnectionFactory;

/**
 * Spring Social Configuration.
 * 
 */
@Configuration
public class SocialConfig {

	@Value("${weibo.client.key}")
	private String weiboKey;

	@Value("${weibo.client.secret}")
	private String weiboSecret;

	@Bean
	@Scope(value = "singleton", proxyMode = ScopedProxyMode.INTERFACES)
	public ConnectionFactoryLocator connectionFactoryLocator() {
		ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
		registry.addConnectionFactory(new WeiboConnectionFactory(weiboKey,
				weiboSecret));
		return registry;
	}

}