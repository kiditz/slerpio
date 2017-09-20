package org.slerpio.service.profile;

import org.slerp.core.CoreException;
import org.slerp.core.Domain;
import org.slerp.core.business.DefaultBusinessFunction;
import org.slerp.core.validation.KeyValidation;
import org.slerpio.entity.Profile;
import org.slerpio.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.slerpio.SlerpIOConstant.Exception.*;

@Service
@KeyValidation("username")
public class FindProfileByUsername extends DefaultBusinessFunction {

	@Autowired
	ProfileRepository profileRepository;

	@Override
	public Domain handle(Domain profileDomain) {
		Profile profile = profileRepository.findProfileByUsername(profileDomain.getString("username"));
		if (profile == null)
			throw new CoreException(PROFILE_NOT_FOUND);
		profile.setSchoolId(null);
		return new Domain().put("profile", profile);
	}
}