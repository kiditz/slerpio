package org.slerpio.service.profile;

import org.slerp.core.business.DefaultBusinessFunction;
import org.slerpio.entity.UserProfile;
import org.springframework.stereotype.Service;
import org.slerp.core.validation.KeyValidation;
import org.slerp.core.validation.NumberValidation;
import org.slerp.core.validation.NotBlankValidation;
import org.slerp.core.Domain;
import org.slerpio.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@KeyValidation("userId")
@NumberValidation({})
@NotBlankValidation({})
public class FindProfileByUserId extends DefaultBusinessFunction {

	@Autowired
	UserProfileRepository userProfileRepository;

	@Override
	public Domain handle(Domain userProfileDomain) {
		UserProfile userProfile = userProfileRepository
				.findProfileById(userProfileDomain.getLong("userId"));
		return new Domain().put("userProfile", userProfile);
	}
}