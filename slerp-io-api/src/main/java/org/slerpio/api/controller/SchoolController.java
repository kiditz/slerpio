package org.slerpio.api.controller;

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

import java.util.Date;

@RestController
public class SchoolController {

	@Autowired
	BusinessFunction getSchoolByName;
	@Autowired
	BusinessTransaction addSchool;

	@Autowired
	BusinessFunction getSchoolByProfileId;
	@Autowired
	BusinessFunction findSchoolById;

	@GetMapping("/getSchoolByName")
	@ResponseBody
	public Domain getSchoolByName(@RequestParam("name") String name,
			@RequestParam("size") Integer size,
			@RequestParam("page") Integer page) {
		Domain schoolDomain = new Domain();
		schoolDomain.put("name", name);
		schoolDomain.put("size", size);
		schoolDomain.put("page", page);
		return getSchoolByName.handle(schoolDomain);
	}

	@PostMapping("/addSchool")
	@ResponseBody
	public Domain addSchool(@RequestBody Domain schoolDomain) {
		schoolDomain.put("activeAt", new Date());
		schoolDomain.put("createdAt", new Date());
		schoolDomain.put("updateAt", new Date());
		schoolDomain.put("active", "Y");
		Domain outputDto = addSchool.handle(schoolDomain);
		return outputDto;
	}

	@GetMapping("/getSchoolByProfileId")
	@ResponseBody
	public Domain getSchoolByProfileId(@RequestParam("profileId") Long profileId) {
		Domain schoolDomain = new Domain();
		schoolDomain.put("profileId", profileId);
		return getSchoolByProfileId.handle(schoolDomain);
	}

	@GetMapping("/findSchoolById")
	@ResponseBody
	public Domain findSchoolById(@RequestParam("schoolId") Long schoolId) {
		Domain schoolDomain = new Domain();
		schoolDomain.put("schoolId", schoolId);
		return findSchoolById.handle(schoolDomain);
	}
}