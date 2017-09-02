package org.slerpio.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@SpringBootApplication
//@EnableOAuth2Sso
@EnableDiscoveryClient
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

/*	@Configuration
	@EnableResourceServer
	protected class Oauth2ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
		private String resourceId = "slerp";

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().antMatchers("/activateUser", "/resend", "/register", "/schoolByName").permitAll().anyRequest()
					.authenticated();
		}

		@Override
		public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
			resources.resourceId(resourceId);
		}
	}*/
}
