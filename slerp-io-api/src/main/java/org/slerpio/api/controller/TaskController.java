package org.slerpio.api.controller;

import java.util.Calendar;

import org.slerp.core.Domain;
import org.slerp.core.business.BusinessFunction;
import org.slerp.core.business.BusinessTransaction;
import org.slerp.core.component.ReactiveHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

	@Autowired
	@Qualifier("addTask")
	BusinessTransaction addTask;
	@Autowired
	@Qualifier("getTaskBySchoolClassId")
	BusinessFunction getTaskBySchoolClassId;
	@Autowired
	ReactiveHandler handler;

	@PostMapping("/addTask")
	@ResponseBody
	public Domain addTask(@RequestBody Domain taskDomain) {
		taskDomain.put("lastUpdate", Calendar.getInstance().getTime());
		return addTask.handle(taskDomain);
	}
}