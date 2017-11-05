package org.slerpio.service.schoolclass;

import org.slerp.core.CoreException;
import org.slerp.core.Domain;
import org.slerp.core.business.DefaultBusinessTransaction;
import org.slerp.core.validation.KeyValidation;
import org.slerp.core.validation.NotBlankValidation;
import org.slerp.core.validation.NumberValidation;
import org.slerpio.ServiceConstant;
import org.slerpio.entity.School;
import org.slerpio.entity.SchoolClass;
import org.slerpio.entity.UserProfile;
import org.slerpio.repository.SchoolClassRepository;
import org.slerpio.repository.SchoolRepository;
import org.slerpio.repository.UserProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@KeyValidation({ "name", "code", "userProfileId", "schoolId", "updateAt", "createdAt" })
@NotBlankValidation({ "name", "code", "userProfileId", "schoolId" })
@NumberValidation({ "userProfileId", "schoolId" })
public class AddSchoolClass extends DefaultBusinessTransaction {

	@Autowired
	SchoolClassRepository schoolClassRepository;
	@Autowired
	SchoolRepository schoolRepository;
	@Autowired
	UserProfileRepository userProfileRepository;
	Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public void prepare(Domain schoolClassDomain) throws Exception {
		Long profileId = schoolClassDomain.getLong("userProfileId");
		Long schoolId = schoolClassDomain.getLong("schoolId");
		if (!userProfileRepository.exists(profileId)) {
			throw new CoreException(ServiceConstant.PROFILE_NOT_FOUND);
		}
		if (!schoolRepository.exists(schoolId)) {
			throw new CoreException(ServiceConstant.SCHOOL_NOT_FOUND);
		}
		UserProfile userProfile = userProfileRepository.findOne(profileId);
		School school = schoolRepository.findOne(schoolId);

		schoolClassDomain.put("userProfileId", userProfile);
		schoolClassDomain.put("school", school);
	}

	@Override
	public Domain handle(Domain schoolClassDomain) {
		super.handle(schoolClassDomain);
		try {
			SchoolClass schoolClass = schoolClassDomain.convertTo(SchoolClass.class);
			UserProfile profile = schoolClassDomain.getDomain("userProfileId").convertTo(UserProfile.class);
			School school = schoolClassDomain.getDomain("school").convertTo(School.class);
			schoolClass.setUserProfileId(profile);
			schoolClass.setSchoolId(school);
			schoolClass = schoolClassRepository.saveAndFlush(schoolClass);
			return new Domain(schoolClass);
		} catch (Exception e) {
			throw new CoreException(e);
		}
	}
}