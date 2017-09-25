package org.slerpio.oauth;

import java.io.IOException;
import java.security.KeyPair;

import org.slerp.core.utils.StreamUtils;
import org.slerpio.oauth.security.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
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

		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			log.info("ENABLE OAUTH SERVER");
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
			KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "kiditz".toCharArray())
					.getKeyPair("jwt");
			converter.setKeyPair(keyPair);
			return converter;
		}
	}

	//
	// @Configuration
	// @EnableResourceServer
	// protected class Oauth2ResourceServerConfiguration extends
	// ResourceServerConfigurerAdapter {
	// private String resourceId = "slerp";
	// @Autowired
	// @Qualifier("authenticationManagerBean")
	// private AuthenticationManager authenticationManager;
	//
	// @Override
	// public void configure(HttpSecurity http) throws Exception {
	// http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and().authorizeRequests()
	// .antMatchers("/login",
	// "/authenticate").permitAll().anyRequest().authenticated();
	// }
	//
	// @Override
	// public void configure(ResourceServerSecurityConfigurer resources) throws
	// Exception {
	// resources.resourceId(resourceId).tokenServices(tokenServices()).stateless(false)
	// .authenticationManager(authenticationManager);
	// }
	//
	// @Bean
	// @Primary
	// public DefaultTokenServices tokenServices() {
	// DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
	// defaultTokenServices.setTokenStore(tokenStore());
	// return defaultTokenServices;
	// }
	//
	// @Bean
	// public TokenStore tokenStore() {
	// return new JwtTokenStore(accessTokenConverter());
	// }
	//
	// @Bean
	// public JwtAccessTokenConverter accessTokenConverter() {
	// JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	// Resource resource = new ClassPathResource("public.txt");
	// String publicKey = null;
	// try {
	// publicKey = StreamUtils.copyStreamToString(resource.getInputStream(),
	// 512);
	// } catch (final IOException e) {
	// throw new RuntimeException(e);
	// }
	// if (log.isDebugEnabled()) {
	// log.debug("Using public key \n: {}", publicKey);
	// }
	// converter.setVerifierKey(publicKey);
	// return converter;
	// }
	// }
}
