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
public class TeacherController {

	@Autowired
	@Qualifier("getTeacherByUsername")
	BusinessFunction getTeacherByUsername;

	@GetMapping("/teacherByUsername")
	@ResponseBody
	public Domain getTeacherByUsername(@RequestParam("username") String username) {
		Domain teacherDomain = new Domain();
		teacherDomain.put("username", username);
		return getTeacherByUsername.handle(teacherDomain);
	}
}