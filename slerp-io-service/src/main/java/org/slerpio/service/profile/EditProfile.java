package org.slerpio.service.profile;

import static org.slerpio.SlerpIOConstant.Exception.PROFILE_NOT_FOUND;
import static org.slerpio.SlerpIOConstant.Exception.USERNAME_HAS_BEEN_USED;

import java.util.Date;

import org.slerp.core.CoreException;
import org.slerp.core.Domain;
import org.slerp.core.business.DefaultBusinessTransaction;
import org.slerp.core.validation.EmailValidation;
import org.slerp.core.validation.KeyValidation;
import org.slerpio.entity.Activity;
import org.slerpio.entity.Profile;
import org.slerpio.repository.ActivityRepository;
import org.slerpio.repository.ProfileRepository;
import org.slerpio.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@KeyValidation({ "oldUsername", "newUsername", "email", "phoneNumber", "fullname", "imagePath", "address" })
@EmailValidation("email")
public class EditProfile extends DefaultBusinessTransaction {

	@Autowired
	ProfileRepository profileRepository;
	@Autowired
	SchoolRepository schoolRepository;
	@Autowired
	ActivityRepository activityRepository;

	@Override
	public void prepare(Domain profileDomain) throws Exception {

		Profile profile = profileRepository.findProfileByUsername(profileDomain.getString("oldUsername"));
		if (profile == null) {
			throw new CoreException(PROFILE_NOT_FOUND);
		}
		if (!isNewUsernameEqualsOldUserName(profileDomain)
				&& profileRepository.isProfileExistByUsername(profileDomain.getString("newUsername")))
			throw new CoreException(USERNAME_HAS_BEEN_USED);
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
			profile = profileRepository.saveAndFlush(profile);

			Activity activity = new Activity();
			activity.setCreatedAt(new Date());
			activity.setLastUpdate(new Date());
			activity.setTitle("activity.profile.edit.title");
			activity.setContent("activity.profile.edit.content");
			activity.setSchoolId(profile.getSchoolId());
			activity = activityRepository.saveAndFlush(activity);
			return new Domain(profile);
		} catch (Exception e) {
			throw new CoreException(e);
		}
	}

	private boolean isNewUsernameEqualsOldUserName(Domain profileDomain) {
		return profileDomain.getString("newUsername").equals(profileDomain.getString("oldUsername"));
	}
}