package org.slerpio.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.cloud.consul.config.ConfigWatch;
import org.springframework.cloud.consul.config.ConsulConfigProperties;
import org.springframework.cloud.consul.config.ConsulPropertySourceLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.ecwid.consul.v1.ConsulClient;

@SpringBootApplication(exclude = { FreeMarkerAutoConfiguration.class, ThymeleafAutoConfiguration.class })
@EnableScheduling
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public ConfigWatch configWatch(ConsulConfigProperties properties, ConsulPropertySourceLocator locator,
			ConsulClient client) {
		return new ConfigWatch(properties, client, locator.getContextIndexes());
	}

}
