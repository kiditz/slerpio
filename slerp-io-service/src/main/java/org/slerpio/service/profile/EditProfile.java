package org.slerpio.service.profile;

import static org.slerpio.SlerpIOConstant.Exception.PROFILE_NOT_FOUND;

import java.util.Date;

import org.slerp.core.CoreException;
import org.slerp.core.Domain;
import org.slerp.core.business.DefaultBusinessTransaction;
import org.slerp.core.validation.EmailValidation;
import org.slerp.core.validation.KeyValidation;
import org.slerp.core.validation.NotBlankValidation;
import org.slerpio.entity.Profile;
import org.slerpio.repository.ProfileRepository;
import org.slerpio.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@KeyValidation({ "oldUsername", "newUsername", "email", "phoneNumber", "fullname", "imagePath", "address" })
@NotBlankValidation({ "oldUsername", "newUsername", "email", "phoneNumber" })
@EmailValidation("email")
public class EditProfile extends DefaultBusinessTransaction {

	@Autowired
	ProfileRepository profileRepository;
	@Autowired
	SchoolRepository schoolRepository;

	@Override
	public void prepare(Domain profileDomain) throws Exception {

		Profile profile = profileRepository.findProfileByUsername(profileDomain.getString("oldUsername"));
		if (profile == null) {
			throw new CoreException(PROFILE_NOT_FOUND);
		}
		profile.setLastUpdate(new Date());
		profile.setUsername(profileDomain.getString("newUsername"));
		profile.setEmail(profileDomain.getString("email"));
		profile.setPhoneNumber(profileDomain.getString("phoneNumber"));
		profile.setFullname(profileDomain.getString("fullname"));
		profile.setImagePath(profileDomain.getString("imagePath"));
		profile.setAddress(profileDomain.getString("address"));
		profileDomain.put("profile", profile);
	}

	@Override
	public Domain handle(Domain profileDomain) {
		super.handle(profileDomain);
		try {
			Profile profile = profileDomain.getDomain("profile").convertTo(Profile.class);			
			profile = profileRepository.save(profile);
			return new Domain(profile);
		} catch (Exception e) {
			throw new CoreException(e);
		}
	}
}