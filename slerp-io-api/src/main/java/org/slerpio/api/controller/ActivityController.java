package org.slerpio.api.controller;

import java.util.Calendar;
import java.util.Date;

import org.slerp.core.Domain;
import org.slerp.core.business.BusinessFunction;
import org.slerp.core.business.BusinessTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActivityController {

	@Autowired
	@Qualifier("addActivity")
	BusinessTransaction addActivity;
	@Autowired
	@Qualifier("getActivityBySchoolId")
	BusinessFunction getActivityBySchoolId;

	@Autowired
	@Qualifier("getActivityBetweenDate")
	BusinessFunction getActivityBetweenDate;

	@PostMapping("/addActivity")
	@ResponseBody
	public Domain addActivity(@RequestBody Domain activityDomain) {
		Domain outputDto = addActivity.handle(activityDomain);
		return outputDto;
	}

	@GetMapping("/getActivityBySchoolId")
	@ResponseBody
	public Domain getActivityBySchoolId(@RequestParam("schoolId") Long schoolId, @RequestParam("page") Integer page,
			@RequestParam("size") Integer size) {
		Domain activityDomain = new Domain();
		activityDomain.put("size", size);
		return getActivityBySchoolId.handle(activityDomain);
	}

	@GetMapping("/getActivityBetweenDate")
	@ResponseBody
	public Domain getActivityBetweenDate(@RequestParam("size") Integer size, @RequestParam("page") Integer page) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR, 24);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		//Date of today
		Date today = calendar.getTime();
		calendar.add(Calendar.DATE, -1);
		//Date of yesterday
		Date yesterday = calendar.getTime();
		Domain activityDomain = new Domain();
		activityDomain.put("size", size);
		activityDomain.put("page", page);
		activityDomain.put("endDate", today);
		activityDomain.put("startDate", yesterday);
		return getActivityBetweenDate.handle(activityDomain);
	}

}