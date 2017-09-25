package org.slerpio.service.profile;

import org.slerp.core.Domain;
import org.slerp.core.business.DefaultBusinessFunction;
import org.slerp.core.validation.KeyValidation;
import org.slerpio.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@KeyValidation("username")
public class IsProfileExistByUsername extends DefaultBusinessFunction {

	@Autowired
	ProfileRepository profileRepository;

	@Override
	public Domain handle(Domain profileDomain) {
		Boolean exists = profileRepository.isProfileExistByUsername(profileDomain.getString("username"));
		return new Domain().put("exists", exists);
	}
}