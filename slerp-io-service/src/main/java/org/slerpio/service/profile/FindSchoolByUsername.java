package org.slerpio.service.profile;

import org.slerp.core.Domain;
import org.slerp.core.business.DefaultBusinessFunction;
import org.slerp.core.validation.KeyValidation;
import org.slerp.core.validation.NotBlankValidation;
import org.slerp.core.validation.NumberValidation;
import org.slerpio.entity.School;
import org.slerpio.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@KeyValidation({ "username", "page", "size" })
@NumberValidation({})
@NotBlankValidation({})
public class FindSchoolByUsername extends DefaultBusinessFunction {

	@Autowired
	ProfileRepository profileRepository;

	@Override
	public Domain handle(Domain schoolDomain) {
		School school = profileRepository.findSchoolByUsername(schoolDomain.getString("username"));
		return new Domain().put("school", school);
	}
}