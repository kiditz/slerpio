package org.slerpio.api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.slerp.core.business.BusinessFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.slerp.core.Domain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.slerp.core.business.BusinessTransaction;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class ProfileController {

	@Autowired
	@Qualifier("findSchoolByUsername")
	BusinessFunction findSchoolByUsername;
	@Autowired
	@Qualifier("dashboardCounter")
	BusinessFunction dashboardCounter;
	@Autowired
	@Qualifier("addProfile")
	BusinessTransaction addProfile;
	@Autowired
	@Qualifier("isProfileExistByUsername")
	BusinessFunction isProfileExistByUsername;
	@Autowired
	@Qualifier("findProfileByUsername")
	BusinessFunction findProfileByUsername;
	@Autowired
	@Qualifier("editProfile")
	BusinessTransaction editProfile;
	@Autowired
	@Qualifier("editPhotoProfile")
	BusinessTransaction editPhotoProfile;
	@Autowired
	@Qualifier("countProfileByAuthority")
	BusinessFunction countProfileByAuthority;
	
	

	@GetMapping("/findSchoolByUsername")
	@ResponseBody
	public Domain findSchoolByUsername(@RequestParam("size") Integer size,
			@RequestParam("username") String username,
			@RequestParam("page") Integer page) {
		Domain profileDomain = new Domain();
		profileDomain.put("page", page);
		return findSchoolByUsername.handle(profileDomain);
	}

	@GetMapping("/dashboardCounter")
	@ResponseBody
	public Domain dashboardCounter(@RequestParam("schoolId") Long schoolId) {
		Domain profileDomain = new Domain();
		profileDomain.put("schoolId", schoolId);
		return dashboardCounter.handle(profileDomain);
	}

	@PostMapping("/addProfile")
	@ResponseBody
	public Domain addProfile(@RequestBody Domain profileDomain) {
		Domain outputDto = addProfile.handle(profileDomain);
		return outputDto;
	}

	@GetMapping("/isProfileExistByUsername")
	@ResponseBody
	public Domain isProfileExistByUsername(
			@RequestParam("username") String username) {
		Domain profileDomain = new Domain();
		profileDomain.put("username", username);
		return isProfileExistByUsername.handle(profileDomain);
	}

	@GetMapping("/findProfileByUsername")
	@ResponseBody
	public Domain findProfileByUsername(
			@RequestParam("username") String username) {
		Domain profileDomain = new Domain();
		profileDomain.put("username", username);
		return findProfileByUsername.handle(profileDomain);
	}

	@PutMapping("/editProfile")
	@ResponseBody
	public Domain editProfile(@RequestBody Domain profileDomain) {
		Domain outputDto = editProfile.handle(profileDomain);
		return outputDto;
	}

	@PutMapping("/editPhotoProfile")
	@ResponseBody
	public Domain editPhotoProfile(@RequestBody Domain profileDomain) {
		Domain outputDto = editPhotoProfile.handle(profileDomain);
		return outputDto;
	}

	@GetMapping("/countProfileByAuthority")
	@ResponseBody
	public Domain countProfileByAuthority(
			@RequestParam("authority") String authority) {
		Domain profileDomain = new Domain();
		profileDomain.put("authority", authority);
		return countProfileByAuthority.handle(profileDomain);
	}
}