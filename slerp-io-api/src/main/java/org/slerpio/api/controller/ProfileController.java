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

import java.util.Date;

@RestController
public class ProfileController {

	@Autowired
	BusinessTransaction addUserProfile;
	@Autowired
	BusinessFunction findProfileByUserId;
	@Autowired
	BusinessFunction isExistsByPhoneNumber;
	@Autowired
	BusinessFunction getProfileByName;
	@Autowired
	BusinessFunction findProfileByPhoneNumber;

	@Autowired
	BusinessFunction getStudentNotHaveClass;

	@PostMapping("/addUserProfile")
	@ResponseBody
	public Domain addUserProfile(@RequestBody Domain profileDomain) {
		profileDomain.put("authority", "STUDENT");
		profileDomain.put("active", "Y");
		profileDomain.put("activeAt", new Date());
		profileDomain.put("createdAt", new Date());
		profileDomain.put("updateAt", new Date());
		Domain outputDto = addUserProfile.handle(profileDomain);
		return outputDto;
	}

	@GetMapping("/findProfileByUserId")
	@ResponseBody
	public Domain findProfileByUserId(@RequestParam("userId") Long userId) {
		Domain profileDomain = new Domain();
		profileDomain.put("userId", userId);
		return findProfileByUserId.handle(profileDomain);
	}

	@GetMapping("/isProfileExistsByPhoneNumber")
	@ResponseBody
	public Domain isExistsByPhoneNumber(
			@RequestParam("phoneNumber") String phoneNumber) {
		Domain profileDomain = new Domain();
		profileDomain.put("phoneNumber", phoneNumber);
		return isExistsByPhoneNumber.handle(profileDomain);
	}

	@GetMapping("/getProfileByName")
	@ResponseBody
	public Domain getProfileByName(@RequestParam("size") Integer size,
			@RequestParam("fullname") String fullname,
			@RequestParam("page") Integer page) {
		Domain profileDomain = new Domain();
		profileDomain.put("size", size);
		profileDomain.put("fullname", fullname);
		profileDomain.put("page", page);
		return getProfileByName.handle(profileDomain);
	}

	@GetMapping("/findProfileByPhoneNumber")
	@ResponseBody
	public Domain findProfileByPhoneNumber(
			@RequestParam("phoneNumber") String phoneNumber) {
		Domain profileDomain = new Domain();
		profileDomain.put("phoneNumber", phoneNumber);
		return findProfileByPhoneNumber.handle(profileDomain);
	}

	@GetMapping("/getStudentNotHaveClass")
	@ResponseBody
	public Domain getStudentNotHaveClass(@RequestParam("classId") Long classId,
			@RequestParam("fullname") String fullname) {
		Domain profileDomain = new Domain();
		profileDomain.put("classId", classId);
		profileDomain.put("fullname", fullname);
		return getStudentNotHaveClass.handle(profileDomain);
	}
}