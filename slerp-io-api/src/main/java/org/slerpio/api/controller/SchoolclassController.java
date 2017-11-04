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
import java.util.UUID;

@RestController
public class SchoolclassController {

	@Autowired
	BusinessTransaction addSchoolClass;
	@Autowired
	BusinessTransaction addStudentToClass;
	@Autowired
	BusinessFunction getSchoolClassByProfileId;

	@Autowired
	BusinessFunction getStudentFromClassId;

	@PostMapping("/addSchoolClass")
	@ResponseBody
	public Domain addSchoolClass(@RequestBody Domain schoolclassDomain) {
		schoolclassDomain.put("code",
				UUID.randomUUID().toString().substring(0, 8).toUpperCase());
		schoolclassDomain.put("createdAt", new Date());
		schoolclassDomain.put("updateAt", new Date());
		Domain outputDto = addSchoolClass.handle(schoolclassDomain);
		return outputDto;
	}

	@PostMapping("/addStudentToClass")
	@ResponseBody
	public Domain addStudentToClass(@RequestBody Domain schoolclassDomain) {
		Domain outputDto = addStudentToClass.handle(schoolclassDomain);
		return outputDto;
	}

	@GetMapping("/getSchoolClassByProfileId")
	@ResponseBody
	public Domain getSchoolClassByProfileId(
			@RequestParam("profileId") Long profileId) {
		Domain schoolclassDomain = new Domain();
		schoolclassDomain.put("profileId", profileId);
		return getSchoolClassByProfileId.handle(schoolclassDomain);
	}

	@GetMapping("/getStudentFromClassId")
	@ResponseBody
	public Domain getStudentFromClassId(@RequestParam("size") Integer size,
			@RequestParam("page") Integer page,
			@RequestParam("schoolClassId") Long schoolClassId) {
		Domain schoolclassDomain = new Domain();
		schoolclassDomain.put("size", size);
		schoolclassDomain.put("page", page);
		schoolclassDomain.put("schoolClassId", schoolClassId);
		return getStudentFromClassId.handle(schoolclassDomain);
	}
}