package org.slerpio.service.activity;

import org.slerp.core.business.DefaultBusinessFunction;
import org.slerpio.entity.Activity;
import org.springframework.stereotype.Service;
import org.slerp.core.validation.KeyValidation;
import org.slerp.core.validation.NumberValidation;
import org.slerp.core.validation.NotBlankValidation;
import org.slerp.core.Domain;
import org.slerpio.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

@Service
@KeyValidation({ "endDate", "startDate", "page", "size" })
@NumberValidation({})
@NotBlankValidation({})
public class GetActivityBetweenDate extends DefaultBusinessFunction {

	@Autowired
	ActivityRepository activityRepository;

	@Override
	public Domain handle(Domain activityDomain) {
		int page = activityDomain.getInt("page");
		int size = activityDomain.getInt("size");
		Page<Activity> activityPage = activityRepository.getActivityBetweenDate(activityDomain.getLong("schoolId"),
				activityDomain.getDate("startDate"), activityDomain.getDate("endDate"),
				new PageRequest(page, size, Direction.DESC, "lastUpdate"));
		return new Domain().put("activityPage", activityPage);
	}
}