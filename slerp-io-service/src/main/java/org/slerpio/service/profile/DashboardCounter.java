package org.slerpio.service.school;

import org.slerp.core.Domain;
import org.slerp.core.business.DefaultBusinessFunction;
import org.slerp.core.validation.KeyValidation;
import org.slerp.core.validation.NotBlankValidation;
import org.slerp.core.validation.NumberValidation;
import org.slerpio.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@KeyValidation("schoolId")
@NumberValidation({})
@NotBlankValidation({})
public class CountProfileBySchoolId extends DefaultBusinessFunction {

	@Autowired
	ProfileRepository profileRepository;

	@Override
	public Domain handle(Domain profileDomain) {
		Long counter = profileRepository.countProfileBySchoolId(profileDomain.getLong("schoolId"));
		return new Domain().put("counter", counter);
	}
}