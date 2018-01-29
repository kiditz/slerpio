package org.slerpio.oauth.controller;

import org.slerp.core.CoreException;
import org.slerp.core.Domain;
import org.slerp.core.business.BusinessTransaction;
import org.slerpio.oauth.OauthConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kiditz 14 September 2017
 */
@RestController
public class Oauth2Controller {
	@Autowired
	@Qualifier("addUserPrincipal")
	private BusinessTransaction addUserPrincipal;
	@Autowired
	KafkaTemplate<String, Domain> kafkaTemplate;

	Logger log = LoggerFactory.getLogger(getClass());

	@PostMapping("/register")
	@ResponseBody
	public Domain addUserPrincipal(@RequestBody Domain oauth2Domain) {
		log.info("add User", oauth2Domain);
		oauth2Domain.put("email", "-");
		if (oauth2Domain.containsKey("password")) {
			String salt = BCrypt.gensalt(10);
			String password = BCrypt.hashpw(oauth2Domain.getString("password"), salt);
			oauth2Domain.put("password", password);

		} else {
			throw new CoreException(OauthConstant.PASSWORD_SHOULD_BE_FILLED);
		}
		oauth2Domain.put("authority", "STUDENT");
		kafkaTemplate.send("register", oauth2Domain);
		Domain outputDto = addUserPrincipal.handle(oauth2Domain);
		return outputDto;		
	}
}