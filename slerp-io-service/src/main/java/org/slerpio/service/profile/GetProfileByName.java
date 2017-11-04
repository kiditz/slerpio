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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@Service
@KeyValidation({"fullname", "page", "size"})
@NumberValidation({})
@NotBlankValidation({})
public class GetProfileByName extends DefaultBusinessFunction {

	@Autowired
	UserProfileRepository userProfileRepository;

	@Override
	public Domain handle(Domain userProfileDomain) {
		int page = userProfileDomain.getInt("page");
		int size = userProfileDomain.getInt("size");
		Page<UserProfile> userProfilePage = userProfileRepository
				.getProfileByName(userProfileDomain.getString("fullname"),
						new PageRequest(page, size));
		return new Domain().put("userProfilePage", userProfilePage);
	}
}