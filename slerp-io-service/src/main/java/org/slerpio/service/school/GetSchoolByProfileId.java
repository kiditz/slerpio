package org.slerpio.service.school;

import java.util.List;

import org.slerp.core.Domain;
import org.slerp.core.business.DefaultBusinessFunction;
import org.slerp.core.validation.KeyValidation;
import org.slerp.core.validation.NotBlankValidation;
import org.slerp.core.validation.NumberValidation;
import org.slerpio.entity.School;
import org.slerpio.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@KeyValidation("profileId")
@NumberValidation({})
@NotBlankValidation({})
public class GetSchoolByProfileId extends DefaultBusinessFunction {

	@Autowired
	UserProfileRepository userProfileRepository;

	@Override
	public Domain handle(Domain userProfileDomain) {
		List<School> userProfileList = userProfileRepository.findSchoolByProfileId(userProfileDomain.getLong("profileId"));
		return new Domain().put("schoolList", userProfileList);
	}
}