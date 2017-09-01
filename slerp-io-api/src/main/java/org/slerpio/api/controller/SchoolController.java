package org.slerpio.api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.slerp.core.business.BusinessFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.slerp.core.Domain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class SchoolController {

	@Autowired
	@Qualifier("getClassBySchoolId")
	BusinessFunction getClassBySchoolId;
	@Autowired
	@Qualifier("getSchoolByName")
	BusinessFunction getSchoolByName;

	@GetMapping("/classBySchoolId")
	@ResponseBody
	public Domain getClassBySchoolId(@RequestParam("schoolId") Long schoolId) {
		Domain schoolDomain = new Domain();
		schoolDomain.put("schoolId", schoolId);
		return getClassBySchoolId.handle(schoolDomain);
	}

	@GetMapping("/schoolByName")
	@ResponseBody
	public Domain getSchoolByName(@RequestParam("size") Integer size, @RequestParam("page") Integer page,
			@RequestParam("schoolName") String schoolName) {
		Domain schoolDomain = new Domain();
		schoolDomain.put("size", size);
		schoolDomain.put("page", page);
		schoolDomain.put("schoolName", schoolName);
		return getSchoolByName.handle(schoolDomain);
	}
}