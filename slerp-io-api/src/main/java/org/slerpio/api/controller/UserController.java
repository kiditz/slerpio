package org.slerpio.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slerp.core.CoreException;
import org.slerp.core.Domain;
import org.slerp.core.business.BusinessTransaction;
import org.slerp.core.component.ReactiveHandler;
import org.slerp.core.utils.Validator;
import org.slerpio.api.listener.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {
	static Logger log = LoggerFactory.getLogger(UserController.class);
	@Autowired
	JmsTemplate jmsTemplate;
	@Autowired
	@Qualifier("addUser")
	BusinessTransaction addUser;
	@Autowired
	ReactiveHandler handler;
	@Autowired
	MessageSource messageSource;
	@Autowired
	Environment env;
	@Autowired
	@Qualifier("removeUser")
	BusinessTransaction removeUser;
	@Autowired
	RedisService redisService;
	@Autowired
	RestTemplate restTemplate;

	@PostMapping("/register")
	@ResponseBody
	public Domain addUser(@RequestBody Domain userDomain) throws Exception {
		try {
			userDomain.put("active", true);
			Domain addDomain = addUser.handle(userDomain);
			Domain requestDomain = new Domain();
			requestDomain.put("username", userDomain.getString("username"));
			requestDomain.put("password", userDomain.getString("password"));
			requestDomain.put("email", userDomain.getString("email"));
			List<Domain> userAuthorityList = new ArrayList<>();
			userAuthorityList.add(new Domain().put("authority", userDomain.getString("type").toUpperCase()));
			requestDomain.put("userAuthorityList", userAuthorityList);
			String url = env.getProperty("slerp.oauth.api.url") + "/register";
			Domain register = restTemplate.postForObject(url, requestDomain, Domain.class);
			log.info("Registration Success {}", register);
			String activationCode = UUID.randomUUID().toString().substring(0, 8);
			jmsTemplate.convertAndSend("slerp.mail", userDomain.put("activationCode", activationCode));
			redisService.setValue(userDomain.getString("username"), activationCode);
			Domain outputDomain = new Domain();
			outputDomain.put("status", 0);
			outputDomain.put("body", addDomain);
			return outputDomain;
		} catch (HttpServerErrorException e) {
			log.error("HttpServerErrorException",e);
			removeUser(userDomain);
			throw new Exception(e);
		}catch (ResourceAccessException e) {
			log.error("ResourceAccessException",e);
			removeUser(userDomain);
			throw new Exception(e);
		}
	}
	
	

	private void removeUser(Domain userDomain) {
		log.debug("Removing user {}", userDomain);
		removeUser.handle(userDomain);
	}



	@PostMapping("activateUser")
	@ResponseBody
	public Domain activate(@RequestBody Domain inputDomain) {
		String url = env.getProperty("slerp.oauth.api.url") + "/activateUser";
		final Domain template = new Domain();
		Domain domain = restTemplate.postForObject(url, inputDomain, Domain.class);
		template.put("status", 0);
		template.put("body", domain);
		return template;
	}

	@GetMapping("resend")
	public Domain resend(@RequestParam(value = "username", required = false) String username) {
		Domain userDomain = new Domain();
		userDomain.put("username", username);
		Validator.validateKey("required.key.@", userDomain, "username");
		String activationCode = (String) redisService.getValue(username);
		if (activationCode == null) {
			throw new CoreException("failed.to.found.activation.code");
		}
		userDomain.put("activationCode", activationCode);
		jmsTemplate.convertAndSend(userDomain);
		userDomain.clear();
		userDomain.put("resend", true);
		return userDomain;
	}
	
}