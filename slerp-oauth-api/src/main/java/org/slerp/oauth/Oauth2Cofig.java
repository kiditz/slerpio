package org.slerp.oauth;

import java.security.KeyPair;

import org.slerp.oauth.security.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

/**
 * The configuration file for spring oauth 2 security
 * 
 * @author kiditz
 */
@Configuration
public class Oauth2Cofig {
	Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	ClientService clientService;

	@Configuration
	@EnableAuthorizationServer
	protected class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
		@Autowired
		@Qualifier("authenticationManagerBean")
		private AuthenticationManager authenticationManager;
		// private String resourceId = "slerp";

		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			log.info("ENABLE OAUTH SERVER");
			// clients.inMemory().withClient("normal-app").authorizedGrantTypes("authorization_code",
			// "implicit")
			// .authorities("ROLE_CLIENT").scopes("read",
			// "write").resourceIds(resourceId).and()
			// clients.inMemory().withClient("trusted-app").authorizedGrantTypes("client_credentials",
			// "password")
			// .authorities("ROLE_TRUSTED_CLIENT").scopes("read",
			// "write").resourceIds(resourceId)
			// .secret("secret");
			clients.withClientDetails(clientService);
		}

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			endpoints.authenticationManager(authenticationManager).accessTokenConverter(jwtAccessTokenConverter());
		}

		@Override
		public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
			security.tokenKeyAccess("permitAll()").checkTokenAccess("hasRole('CLIENT')");
		}

		@Bean
		public JwtAccessTokenConverter jwtAccessTokenConverter() {
			JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
			KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"),
					"RIFKYADITYABASTARA".toCharArray()).getKeyPair("jwt");
			converter.setKeyPair(keyPair);
			return converter;
		}
	}

	@EnableResourceServer
	protected class Oauth2ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
		private String resourceId = "slerp";

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().antMatchers("/activateUser", "/register").permitAll().anyRequest().authenticated();
		}

		@Override
		public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
			resources.resourceId(resourceId);
		}
	}
}