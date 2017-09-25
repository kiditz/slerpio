package org.slerpio.api;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slerp.core.component.ReactiveHandler;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

@Configuration
@EntityScan(basePackages = { "org.slerpio.entity" })
@EnableJpaRepositories(basePackages = { "org.slerpio.repository" })
@EnableTransactionManagement
@ComponentScan(basePackages = { "org.slerpio.service", "org.slerp.core.component", "org.slerpio.api.listener" })
public class RepositoryConfiguration {
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public LocaleResolver localeResolver() {
		SmartLocaleResolver slr = new SmartLocaleResolver();
		slr.setDefaultLocale(Locale.US); // Set default Locale as US
		return slr;
	}

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasenames("i18n/messages"); // name of the resource bundle
		source.setUseCodeAsDefaultMessage(true);
		return source;
	}

	public class SmartLocaleResolver extends CookieLocaleResolver {
		@Override
		public Locale resolveLocale(HttpServletRequest request) {
			String acceptLanguage = request.getHeader("Accept-Language");
			if (acceptLanguage == null || acceptLanguage.trim().isEmpty()) {
				return super.determineDefaultLocale(request);
			}
			return request.getLocale();
		}
	}

	@Bean
	public ReactiveHandler reactiveHandler(MessageSource messageSource) {
		return new ReactiveHandler(messageSource);
	}

	@Bean
	public ByteArrayHttpMessageConverter byteArrayHttpMessageConverter() {
		ByteArrayHttpMessageConverter converter = new ByteArrayHttpMessageConverter();
//		List<MediaType> supportedMediaTypes = new ArrayList<>();
//		supportedMediaTypes.add(MediaType.IMAGE_PNG);
//		supportedMediaTypes.add(MediaType.IMAGE_JPEG);
//		supportedMediaTypes.add(MediaType.IMAGE_GIF);
//		supportedMediaTypes.add(MediaType.APPLICATION_PDF);		
//		converter.setSupportedMediaTypes(supportedMediaTypes);
		return converter;
	}
}