package org.slerpio.api;

import java.io.IOException;

import org.slerp.core.utils.StreamUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.cloud.consul.config.ConfigWatch;
import org.springframework.cloud.consul.config.ConsulConfigProperties;
import org.springframework.cloud.consul.config.ConsulPropertySourceLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import com.ecwid.consul.v1.ConsulClient;

@SpringBootApplication(exclude = { FreeMarkerAutoConfiguration.class, ThymeleafAutoConfiguration.class })
@EnableScheduling
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Configuration
	@EnableResourceServer
	protected class Oauth2ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
		private String resourceId = "slerp";

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().antMatchers("/activateUser", "/resend", "/register", "/schoolByName").permitAll()
					.anyRequest().authenticated();
		}

		@Override
		public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
			resources.resourceId(resourceId);
		}

		@Bean
		public JwtAccessTokenConverter accessTokenConverter() {
			JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
			Resource resource = new ClassPathResource("public.txt");
			String publicKey = null;
			try {
				publicKey = StreamUtils.copyStreamToString(resource.getInputStream(), 512);
			} catch (final IOException e) {
				throw new RuntimeException(e);
			}
			System.out.println(publicKey);
			converter.setVerifierKey(publicKey);
			return converter;
		}
		//Enable refresh without restartb
		@Bean
		public ConfigWatch configWatch(ConsulConfigProperties properties, ConsulPropertySourceLocator locator,
				ConsulClient client) {
			return new ConfigWatch(properties, client, locator.getContextIndexes());
		}
	}

}
