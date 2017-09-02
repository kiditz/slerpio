package org.slerpio.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//@EnableOAuth2Sso
@EnableDiscoveryClient
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	@Configuration
//	@EnableResourceServer
//	protected class Oauth2ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
//		private String resourceId = "slerp";
//
//		@Override
//		public void configure(HttpSecurity http) throws Exception {
//			http.authorizeRequests().antMatchers("/activateUser", "/resend", "/register", "/schoolByName").permitAll().anyRequest()
//					.authenticated();
//		}
//
//		@Override
//		public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//			resources.resourceId(resourceId);
//		}
//	}
}
