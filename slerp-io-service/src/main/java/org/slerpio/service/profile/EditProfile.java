package org.slerpio.service.profile;

import java.util.Date;

import org.slerp.core.CoreException;
import org.slerp.core.Domain;
import org.slerp.core.business.DefaultBusinessTransaction;
import org.slerp.core.validation.EmailValidation;
import org.slerp.core.validation.KeyValidation;
import org.slerp.core.validation.NotBlankValidation;
import org.slerp.core.validation.NumberValidation;
import org.slerpio.entity.Profile;
import org.slerpio.repository.ProfileRepository;
import org.slerpio.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@KeyValidation({ "profileId", "username", "email", "phoneNumber", "fullname", "imagePath", "address" })
@NotBlankValidation({ "username", "email", "phoneNumber" })
@NumberValidation("profileId")
@EmailValidation("email")
public class EditProfile extends DefaultBusinessTransaction {

	@Autowired
	ProfileRepository profileRepository;
	@Autowired
	SchoolRepository schoolRepository;

	@Override
	public void prepare(Domain profileDomain) throws Exception {

		Profile profile = profileRepository.findOne(profileDomain.getLong("profileId"));
		if (profile == null) {
			throw new CoreException("org.slerpio.entity.Profile.notFound");
		}
	}

	@Override
	public Domain handle(Domain profileDomain) {
		super.handle(profileDomain);
		try {
			Profile profile = profileDomain.convertTo(Profile.class);
			profile.setLastUpdate(new Date());
			profile = profileRepository.save(profile);
			return new Domain(profile);
		} catch (Exception e) {
			throw new CoreException(e);
		}
	}
}