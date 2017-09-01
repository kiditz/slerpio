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
public class StudentController {

	@Autowired
	@Qualifier("getStudentByUsername")
	BusinessFunction getStudentByUsername;
	@Autowired
	@Qualifier("getStudentByClassId")
	BusinessFunction getStudentByClassId;

	@GetMapping("/studentByUsername")
	@ResponseBody
	public Domain getStudentByUsername(@RequestParam("username") String username) {
		Domain studentDomain = new Domain();
		studentDomain.put("username", username);
		return getStudentByUsername.handle(studentDomain);
	}

	@GetMapping("/studentByClassId")
	@ResponseBody
	public Domain getStudentByClassId(@RequestParam("schoolClassId") Long schoolClassId) {
		Domain studentDomain = new Domain();
		studentDomain.put("schoolClassId", schoolClassId);
		return getStudentByClassId.handle(studentDomain);
	}
}