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

@RestController
public class TaskController {

	@Autowired
	BusinessTransaction addTask;

	@Autowired
	BusinessFunction getTaskFromSchool;

	@Autowired
	BusinessFunction getTaskFromProfile;

	@Autowired
	BusinessFunction getTaskByTeacher;

	@PostMapping("/addTask")
	@ResponseBody
	public Domain addTask(@RequestBody Domain taskDomain) {
		Domain outputDto = addTask.handle(taskDomain);
		return outputDto;
	}

	@GetMapping("/getTaskFromSchool")
	@ResponseBody
	public Domain getTaskFromSchool(@RequestParam("size") Integer size, @RequestParam("schoolId") Long schoolId,
			@RequestParam("page") Integer page) {
		Domain taskDomain = new Domain();
		taskDomain.put("size", size);
		taskDomain.put("schoolId", schoolId);
		taskDomain.put("page", page);
		return getTaskFromSchool.handle(taskDomain);
	}

	@GetMapping("/getTaskByStudent")
	@ResponseBody
	public Domain getTaskFromProfile(@RequestParam("page") Integer page, @RequestParam("profileId") Long profileId,
			@RequestParam("size") Integer size) {
		Domain taskDomain = new Domain();
		taskDomain.put("page", page);
		taskDomain.put("profileId", profileId);
		taskDomain.put("size", size);
		return getTaskFromProfile.handle(taskDomain);
	}

	@GetMapping("/getTaskByTeacher")
	@ResponseBody
	public Domain getTaskByTeacher(@RequestParam("page") Integer page, @RequestParam("size") Integer size,
			@RequestParam("profileId") Long profileId) {
		Domain taskDomain = new Domain();
		taskDomain.put("page", page);
		taskDomain.put("size", size);
		taskDomain.put("profileId", profileId);
		return getTaskByTeacher.handle(taskDomain);
	}
}