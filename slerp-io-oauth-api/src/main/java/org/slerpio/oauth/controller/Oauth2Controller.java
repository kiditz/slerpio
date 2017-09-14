package org.slerpio.oauth.controller;

import org.springframework.web.bind.annotation.RestController;
import org.slerp.core.business.BusinessFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.slerp.core.Domain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.slerp.core.business.BusinessTransaction;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
public class Oauth2Controller {

	@Autowired
	BusinessFunction findClientByClientId;
	@Autowired
	BusinessTransaction addClient;
	@Autowired
	BusinessTransaction editUserPrincipal;
	@Autowired
	BusinessTransaction removeUserPrincipal;
	@Autowired
	BusinessFunction getAuthorityByUsername;
	@Autowired
	BusinessTransaction addUserPrincipal;
	@Autowired
	BusinessFunction findUserPrincipalByUsername;

	@GetMapping("/findClientByClientId")
	@ResponseBody
	public Domain findClientByClientId(@RequestParam("clientId") String clientId) {
		Domain oauth2Domain = new Domain();
		oauth2Domain.put("clientId", clientId);
		return findClientByClientId.handle(oauth2Domain);
	}

	@PostMapping("/addClient")
	@ResponseBody
	public Domain addClient(@RequestBody Domain oauth2Domain) {
		Domain outputDto = addClient.handle(oauth2Domain);
		return outputDto;
	}

	@PutMapping("/editUserPrincipal")
	@ResponseBody
	public Domain editUserPrincipal(@RequestBody Domain oauth2Domain) {
		Domain outputDto = editUserPrincipal.handle(oauth2Domain);
		return outputDto;
	}

	@DeleteMapping("/removeUserPrincipal")
	@ResponseBody
	public Domain removeUserPrincipal(@RequestBody Domain oauth2Domain) {
		Domain outputDto = removeUserPrincipal.handle(oauth2Domain);
		return outputDto;
	}

	@GetMapping("/getAuthorityByUsername")
	@ResponseBody
	public Domain getAuthorityByUsername(@RequestParam("username") String username) {
		Domain oauth2Domain = new Domain();
		oauth2Domain.put("username", username);
		return getAuthorityByUsername.handle(oauth2Domain);
	}

	@PostMapping("/register")
	@ResponseBody
	public Domain addUserPrincipal(@RequestBody Domain oauth2Domain) {
		Domain outputDto = addUserPrincipal.handle(oauth2Domain);
		return outputDto;
	}

	@GetMapping("/findUserPrincipalByUsername")
	@ResponseBody
	public Domain findUserPrincipalByUsername(@RequestParam("username") String username) {
		Domain oauth2Domain = new Domain();
		oauth2Domain.put("username", username);
		return findUserPrincipalByUsername.handle(oauth2Domain);
	}
}