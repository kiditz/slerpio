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

@Service
@KeyValidation({ "schoolId", "page", "size" })
@NumberValidation({})
@NotBlankValidation({})
public class GetActivityBySchoolId extends DefaultBusinessFunction {

	@Autowired
	ActivityRepository activityRepository;

	@Override
	public Domain handle(Domain activityDomain) {
		int page = activityDomain.getInt("page");
		int size = activityDomain.getInt("size");
		Page<Activity> activityPage = activityRepository.findActivityBySchoolId(activityDomain.getLong("schoolId"),
				new PageRequest(page, size));
		return new Domain().put("activityPage", activityPage);
	}
}