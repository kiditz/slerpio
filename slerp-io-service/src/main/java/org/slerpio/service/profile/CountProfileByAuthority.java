package org.slerpio.service.profile;

import org.slerp.core.Domain;
import org.slerp.core.business.DefaultBusinessFunction;
import org.slerp.core.validation.KeyValidation;
import org.slerp.core.validation.NotBlankValidation;
import org.slerp.core.validation.NumberValidation;
import org.slerpio.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@KeyValidation("authority")
@NumberValidation({})
@NotBlankValidation({ "authority" })
public class CountProfileByAuthority extends DefaultBusinessFunction {

	@Autowired
	ProfileRepository profileRepository;

	@Override
	public Domain handle(Domain profileDomain) {
		Long counter = profileRepository.countProfileByAuthority(profileDomain.getString("authority"));
		return new Domain().put("counter", counter);
	}
}