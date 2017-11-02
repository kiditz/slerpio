package org.slerpio.service.profile;

import org.slerp.core.Domain;
import org.slerp.core.business.DefaultBusinessFunction;
import org.slerp.core.validation.KeyValidation;
import org.slerp.core.validation.NotBlankValidation;
import org.slerp.core.validation.NumberValidation;
import org.slerpio.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@KeyValidation("phoneNumber")
@NumberValidation({})
@NotBlankValidation({})
public class IsExistsByPhoneNumber extends DefaultBusinessFunction {

	@Autowired
	UserProfileRepository userProfileRepository;

	@Override
	public Domain handle(Domain userProfileDomain) {
		Boolean exists = userProfileRepository.isExistsByPhoneNumber(userProfileDomain.getString("phoneNumber"));
		return new Domain().put("exists", exists);
	}
}