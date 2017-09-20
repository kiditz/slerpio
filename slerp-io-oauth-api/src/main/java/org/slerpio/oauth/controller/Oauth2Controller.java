package org.slerpio.oauth.controller;

import org.slerp.core.CoreException;
import org.slerp.core.Domain;
import org.slerp.core.business.BusinessFunction;
import org.slerp.core.business.BusinessTransaction;
import org.slerpio.oauth.OauthConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;
/**
 * @author kiditz 14 September 2017
 */
@RestController
public class Oauth2Controller {
	@Autowired
	KafkaTemplate<String, Domain> kafkaTemplate;
	@Value("${kafka.messages.register}")
	private String topicRegister;
	@Autowired
	BusinessFunction findClientByClientId;
	@Autowired
	BusinessTransaction addClient;
	@Autowired
	BusinessTransaction activateUser;
	@Autowired
	BusinessTransaction removeUserPrincipal;
	@Autowired
	BusinessFunction getAuthorityByUsername;
	@Autowired
	BusinessTransaction addUserPrincipal;
	@Autowired
	BusinessFunction findUserPrincipalByUsername;

	@GetMapping("/client")
	@ResponseBody
	public Domain findClientByClientId(@RequestParam("clientId") String clientId) {
		Domain oauth2Domain = new Domain();
		oauth2Domain.put("clientId", clientId);
		return findClientByClientId.handle(oauth2Domain);
	}

	@PostMapping("/client")
	@ResponseBody
	public Domain addClient(@RequestBody Domain oauth2Domain) {
		Domain outputDto = addClient.handle(oauth2Domain);
		return outputDto;
	}

	@PutMapping("/activateUser")
	@ResponseBody
	public Domain activateUser(@RequestBody Domain oauth2Domain) {
		Domain outputDto = activateUser.handle(oauth2Domain);
		return outputDto;
	}

	@PostMapping("/register")
	@ResponseBody
	public Domain addUserPrincipal(@RequestBody Domain oauth2Domain) {
		if (oauth2Domain.containsKey("password")) {
			String salt = BCrypt.gensalt(10);
			String password = BCrypt.hashpw(oauth2Domain.getString("password"), salt);
			oauth2Domain.put("password", password);
		} else {
			throw new CoreException(OauthConstant.PASSWORD_SHOULD_BE_FILLED);
		}
		if (!oauth2Domain.containsKey("schoolId") || oauth2Domain.getDomain("schoolId") == null) {
			throw new CoreException(OauthConstant.SCHOOL_SHOULD_BE_FILLED);
		}
		Domain outputDto = addUserPrincipal.handle(oauth2Domain);
		outputDto.put("schoolId", oauth2Domain.getDomain("schoolId"));
		kafkaTemplate.send(topicRegister, outputDto);
		return outputDto;
	}

	@GetMapping("/user")
	@ResponseBody
	public Domain findUserPrincipalByUsername(@RequestParam("username") String username) {
		Domain oauth2Domain = new Domain();
		oauth2Domain.put("username", username);
		Domain outputDomain = findUserPrincipalByUsername.handle(oauth2Domain);
		outputDomain.getDomain("userPrincipal").remove("hashedPassword");
		return outputDomain;
	}

	@GetMapping("/me")
	@ResponseBody
	public Principal getInfo(Principal principal){
		return principal;
	}
}