package org.slerpio.service.activity;

import static org.slerpio.SlerpIOConstant.Exception.SCHOOL_NOT_FOUND;

import java.util.Date;

import org.slerp.core.CoreException;
import org.slerp.core.Domain;
import org.slerp.core.business.DefaultBusinessTransaction;
import org.slerp.core.validation.KeyValidation;
import org.slerp.core.validation.NotBlankValidation;
import org.slerpio.entity.Activity;
import org.slerpio.entity.School;
import org.slerpio.repository.ActivityRepository;
import org.slerpio.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@KeyValidation({ "title", "content", "schoolId" })
@NotBlankValidation({ "title", "content", "schoolId" })
public class AddActivity extends DefaultBusinessTransaction {

	@Autowired
	ActivityRepository activityRepository;
	@Autowired
	SchoolRepository schoolRepository;

	@Override
	public void prepare(Domain activityDomain) throws Exception {
		Domain schoolDomain = activityDomain.getDomain("schoolId");
		School schoolId = schoolDomain.convertTo(School.class);
		schoolId = schoolRepository.findOne(schoolDomain.getLong("schoolId"));
		if (schoolId == null) {
			throw new CoreException(SCHOOL_NOT_FOUND);
		}

		activityDomain.put("schoolId", schoolId);
		activityDomain.put("createdAt", new Date());
		activityDomain.put("lastUpdate", new Date());
	}

	@Override
	public Domain handle(Domain activityDomain) {
		super.handle(activityDomain);
		try {
			Activity activity = activityDomain.convertTo(Activity.class);
			activity = activityRepository.save(activity);
			return new Domain(activity);
		} catch (Exception e) {
			throw new CoreException(e);
		}
	}
}