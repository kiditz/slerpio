

package org.slerp.oauth.controller;

import org.slerp.core.CoreException;
import org.slerp.core.Domain;
import org.slerp.core.business.BusinessFunction;
import org.slerp.core.business.BusinessTransaction;
import org.slerp.oauth.security.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Oauth2Controller {

	Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	BusinessTransaction addUserPrincipal;
	@Autowired
	BusinessFunction findUserPrincipalByUsername;
	@Autowired
	BusinessFunction getAuthorityByUsername;
	@Autowired
	BusinessTransaction removeUserPrincipal;
	@Autowired
	BusinessTransaction editUserPrincipal;
	@Autowired
	RedisService redisService;

	/**
	 * {@link #addUserPrincipal(Domain)} will be insert into table
	 * user_principal and then send you email with the activation code
	 * 
	 * @param oauth2Domain
	 *            need json
	 * 
	 *            <pre>
	 *{
	 *  "username": {@link String}
	 *  "password" : {@link String}
	 *  "email" : {@link String}
	 *}
	 *            </pre>
	 */
	@CrossOrigin
	@PostMapping("/register")
	@ResponseBody
	public Domain addUserPrincipal(@RequestBody Domain oauth2Domain) throws CoreException {
		String salt = BCrypt.gensalt(10);
		oauth2Domain.put("password", BCrypt.hashpw(oauth2Domain.getString("password"), salt));
		// oauth2Domain.put("activationCode", activationCode);
		Domain outputDomain = addUserPrincipal.handle(oauth2Domain);
		// Handle Activation code only active in 24 hours
		Domain resultDomain = new Domain();
		resultDomain.put("username", outputDomain.getString("username"));
		resultDomain.put("success", true);
		return resultDomain;
	}

	/**
	 * Activate User with activation code, the input should be something like
	 * this
	 * 
	 * @param inputDomain
	 *            need json
	 * 
	 *            <pre>
	 *{
	 *  "username": {@link String}
	 *  "activationCode" : {@link String}
	 *}
	 *            </pre>
	 */
	@CrossOrigin
	@PostMapping("/activateUser")
	@ResponseBody
	public Domain activateUser(@RequestBody Domain inputDomain) throws CoreException {
		String activationCode = null;
		activationCode = (String) redisService.getValue(inputDomain.getString("username"));
		if (activationCode == null)
			throw new CoreException("failed.to.found.activation.code");
		if (!activationCode.equals(inputDomain.getString("activationCode"))) {
			throw new CoreException("the.activation.code.is.not.yours");
		}

		log.info("oauthDomain : {}", activationCode);
		Domain userDomain = new Domain();
		userDomain.put("username", inputDomain.getString("username"));
		Domain getUser = findUserPrincipalByUsername.handle(userDomain).getDomain("userPrincipal");
		getUser.remove("userAuthorityList");
		if (getUser.getBoolean("enabled"))
			throw new CoreException("the.account.was.active");
		getUser.put("enabled", true);
		getUser = editUserPrincipal.handle(getUser);
		redisService.delete(inputDomain.getString("username"));
		return getUser;
	}

	@GetMapping("authorities")
	public Domain getUserAuthority(@RequestParam("username") String username) {
		Domain inputDomain = new Domain().put("username", username);
		return getAuthorityByUsername.handle(inputDomain);
	}

	@PutMapping("editUser")
	public Domain updateUser(@RequestParam("username") String username, @RequestBody Domain userDomain) {
		String salt = BCrypt.gensalt(10);
		Domain user = findUserPrincipalByUsername.handle(userDomain.put("username", username)).getDomain("userPrincipal");
		user.put("email", userDomain.getString("email"));
		user.put("username", userDomain.getString("username"));
		user.put("password", BCrypt.hashpw(userDomain.getString("password"), salt));
		return editUserPrincipal.handle(userDomain);
	}
}
