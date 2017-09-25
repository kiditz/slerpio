package org.slerpio.api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.slerp.core.business.BusinessTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.slerp.core.Domain;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.slerp.core.business.BusinessFunction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class SchoolController {

	@Autowired
	BusinessTransaction editSchool;
	@Autowired
	BusinessFunction countProfileBySchoolId;
	@Autowired
	BusinessFunction getSchoolByName;
	@Autowired
	BusinessTransaction addSchool;

	@PutMapping("/editSchool")
	@ResponseBody
	public Domain editSchool(@RequestBody Domain schoolDomain) {
		Domain outputDto = editSchool.handle(schoolDomain);
		return outputDto;
	}

	@GetMapping("/totalPeople")
	@ResponseBody
	public Domain countProfileBySchoolId(@RequestParam("schoolId") Long schoolId) {
		Domain schoolDomain = new Domain();
		schoolDomain.put("schoolId", schoolId);
		return countProfileBySchoolId.handle(schoolDomain);
	}

	@GetMapping("/getSchoolByName")
	@ResponseBody
	public Domain getSchoolByName(@RequestParam("name") String name, @RequestParam("size") Integer size,
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
		Domain outputDto = addSchool.handle(schoolDomain);
		return outputDto;
	}
}