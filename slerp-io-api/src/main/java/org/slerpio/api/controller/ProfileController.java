package org.slerpio.api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.slerp.core.business.BusinessTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.slerp.core.Domain;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.slerp.core.business.BusinessFunction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class ProfileController {

	@Autowired
	BusinessTransaction addProfile;
	@Autowired
	BusinessFunction findProfileByUsername;
	@Autowired
	BusinessTransaction editProfile;

	@PostMapping("/profile")
	@ResponseBody
	public Domain addProfile(@RequestBody Domain profileDomain) {
		Domain outputDto = addProfile.handle(profileDomain);
		return outputDto;
	}

	@GetMapping("/profile")
	@ResponseBody
	public Domain findProfileByUsername(@RequestParam("username") String username) {
		Domain profileDomain = new Domain();
		profileDomain.put("username", username);
		return findProfileByUsername.handle(profileDomain);
	}

	@PutMapping("/profile")
	@ResponseBody
	public Domain editProfile(@RequestBody Domain profileDomain) {
		Domain outputDto = editProfile.handle(profileDomain);
		return outputDto;
	}
}