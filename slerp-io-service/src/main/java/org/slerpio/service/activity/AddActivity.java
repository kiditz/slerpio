package org.slerpio.service.activity;

import java.util.Date;

import org.slerp.core.CoreException;
import org.slerp.core.Domain;
import org.slerp.core.business.DefaultBusinessTransaction;
import org.slerp.core.validation.KeyValidation;
import org.slerp.core.validation.NotBlankValidation;
import org.slerpio.entity.Activity;
import org.slerpio.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@KeyValidation({ "title", "content" })
@NotBlankValidation({ "title", "content" })
public class AddActivity extends DefaultBusinessTransaction {

	@Autowired
	ActivityRepository activityRepository;

	@Override
	public void prepare(Domain activityDomain) throws Exception {
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